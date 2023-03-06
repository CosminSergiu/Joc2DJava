package PaooGame;
import java.sql.*;

import PaooGame.BazaDate.BazaDate;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Level.Level;
import PaooGame.Level.Level1;
import PaooGame.State.HelpState;
import PaooGame.State.PierdereState;
import PaooGame.State.StateDraw;
import PaooGame.State.VictorieState;
import PaooGame.Tiles.TileManager;
import PaooGame.entity.*;
import PaooGame.object.inima;
import PaooGame.Menu.Menu;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;



/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable{

    Sound sound = new Sound();

    public state gameState = state.Meniu;
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic()
    {
        sound.stop();
    }
    public GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean runState;   /*!< Flag ce starea firului de executie.*/
    public BufferStrategy bs;
    private final KeyboardHandler KeyH = new KeyboardHandler() ;
    public TileManager map;
    {
        try {
            map = new TileManager(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    BazaDate sqlScore = BazaDate.getInstance();
    private HelpState help = new HelpState();
    public inima coins= new inima();
    public Macron macron = new Macron(this);
    public Biden biden = new Biden(this);
    public Boris boris = new Boris(this);
    public Collision cCollision=new Collision(this);
    public Player putin=new Player(this,KeyH);
    public Menu meniu = Menu.creareMeniu(this);
    private StateDraw stari;
    public Nivel nivel;
    public Level level;

    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************


    //private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    public Game(String title, int width, int height){
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();


        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    public void InitGame() {
        wnd = new GameWindow("The URSS II-Project", 875, 975);
            /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        nivel = Nivel.Level1;
        level = new Level1(this);
            /// Se incarca toate elementele grafice (dale)
        Assets.Init();

    }
    public void drawScore(Graphics2D g) {
        String s;

        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(new Color(10));
        s = "Score: " + putin.scor;
        g.drawString(s, 0, 25*39-10);
    }
    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        playMusic(0);
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/
        try {
            TileManager map = new TileManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame = 1000000000.0 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState) {

            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if ((curentTime - oldTime) > timeFrame) {
                /// Actualizeaza pozitiile elementelor

                try {
                    Update();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                /// Deseneaza elementele grafica in fereastra.
                try {
                    Draw();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        Thread gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
        if (!runState) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }

    }

   /*fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.

    public synchronized void StopGame()
    {
        if(runState)
        {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
                /// Thread-ul este oprit deja.
            return;
        }
    }*/

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() throws SQLException {

        if(gameState == state.Game) {
            putin.Update();
            biden.Update();
            macron.Update();
            boris.Update();
            if(KeyH.poz[4]){
                gameState = state.Meniu;
            }
            level.Update();
        }else if(gameState == state.Meniu){

            meniu.update();
        }
        else if(gameState == state.Victorie || gameState == state.Pierdere ){

            sqlScore.insertNewScore(putin.scor);
            //sqlScore.Close();
            if(KeyH.poz[4]){
                gameState = state.Meniu;
            }
        }
        else if(gameState == state.Meniu){
            if(KeyH.poz[4]){
                gameState = state.Meniu;
            }
        }else if(gameState == state.Help) {
            if (KeyH.poz[4]) {
                gameState = state.Meniu;
            }
        }
        //wnd.GetCanvas().addKeyListener(KeyH);
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    public void Draw() throws InterruptedException {
        Graphics g;          /*!< Referinta catre un context grafic.*/
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();

        /// Verific daca buffer strategy a fost construit sau nu
        if (bs == null) {
            /// Se executa doar la primul apel al metodei Draw()
            try {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.

        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        /// operatie de desenare
        if(gameState == state.Game) {
            level.Draw(g);
        }
        else if(gameState == state.Meniu){
            meniu.Draw(g);
        }
        else if(gameState == state.Victorie && nivel == Nivel.Level2){
            stari = new VictorieState();
            stari.Draw(g);
        }
        else if(gameState == state.Help){
            stari = new HelpState();
            stari.Draw(g);
        }
        else if(gameState == state.Pierdere&& nivel == Nivel.Level2){
            stari = new PierdereState();
            stari.Draw(g);
        }

        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }
}

