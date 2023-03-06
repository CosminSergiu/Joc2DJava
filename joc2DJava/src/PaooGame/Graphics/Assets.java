package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.public static BufferedImage pamant;
    public static BufferedImage zidCaramida;
    public static BufferedImage zidPiatra;
    public static BufferedImage tank;
    public static BufferedImage putin;
    public static BufferedImage biden;
    public static BufferedImage macron;
    public static BufferedImage boris;
    public static BufferedImage inima;
    public static BufferedImage pamant;
    public static BufferedImage menu;
    public static BufferedImage victorie;
    public static BufferedImage pierdere;
    public static BufferedImage help;



    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/texturi.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        pamant = sheet.crop(0, 0);
        zidCaramida = sheet.crop(1, 0);
        zidPiatra = sheet.crop(2, 0);
        tank= sheet.crop(3, 0);
        putin = sheet.crop(4, 0);
        biden= sheet.crop(5, 0);
        macron= sheet.crop(6, 0);
        boris = sheet.crop(7, 0);
        inima = sheet.crop(8, 0);
        menu = ImageLoader.LoadImage("/textures/poster.png");
        victorie = ImageLoader.LoadImage("/textures/victorie.png");
        pierdere = ImageLoader.LoadImage("/textures/pierdere.png");
        help = ImageLoader.LoadImage("/textures/Help.png");


    }
}
