package PaooGame.Tiles;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    //private static final int NO_TILES   = 5;
    //public static Tile[] tiles          = new Tile[NO_TILES];/*!< Vector de referinte de tipuri de dale.*/
    Game gp;

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    //public static Tile pamantTile= new PamantTile(0);
    //public static Tile zidCaramidaTile= new ZidCaramidaTile(1);
    //public static Tile zidPiatraTile= new ZidPiatraTile(2);
    //public static Tile tankTile= new TankTile(3);
    //public static Tile putin=new Putin(4);
    public static final int TILE_WIDTH  = 39;                     /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 35;                       /*!< Inaltimea unei dale.*/
    public BufferedImage img;
    public boolean CollesioOn =false;
    //protected final int id;
    //protected static int mapTile[][] = new int[25][25];

    /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
   /* public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     /
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     /

    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);

        // g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);



    }

    /* \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
*/
    public boolean IsSolid()
    {
        return false;
    }

     //fn public int GetId()
        //brief Returneaza id-ul dalei.

   /* public int GetId()
    {
        return id;
    }

*/
}
