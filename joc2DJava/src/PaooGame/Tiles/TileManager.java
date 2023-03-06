package PaooGame.Tiles;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Nivel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Game gp;
    public Tile [] tile;
    public int[][] mapTile =new int [25][25];
    public TileManager(Game gp) throws IOException {
        this.gp=gp;
        tile = new Tile[6];
        Assets.Init();
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        tile[0]=new Tile();
        tile[0].img = Assets.pamant;
        tile[1]=new Tile();
        tile[1].img = Assets.zidCaramida;
        tile[1].CollesioOn = true;
        tile[2]=new Tile();
        tile[2].img = Assets.zidPiatra;
        tile[2].CollesioOn = true;
        tile[3]=new Tile();
        tile[3].img = Assets.tank;
        tile[3].CollesioOn = true;
        tile[4]=new Tile();
        tile[4].img = Assets.putin;
        tile[5]= new Tile();
        tile[5].img = Assets.inima;
        tile[5].CollesioOn = false;
    }
    public void loadMap()  {
        try {
            InputStream is;
            if(gp.nivel == Nivel.Level1) {
                is = getClass().getResourceAsStream("/textures/map.txt");
            }
            else{
                is = getClass().getResourceAsStream("/textures/map2.txt");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < 26 && row < 25) {
                String line = br.readLine();
                while (col < 25) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[col][row] = num;
                    col++;
                }
                if (col == 25) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Draw(Graphics g){

        int col =0;
        int row =0;
        int y = 0;
        int x = 0;
        while(col<26 && row< 25 ){
            int tileNum=mapTile[col][row];
            if(tileNum == 5){
                g.drawImage(tile[0].img,x,y, Tile.TILE_HEIGHT,Tile.TILE_WIDTH,null);
                gp.coins.Draw(g, x+10, y);
            }
            else{
                g.drawImage(tile[tileNum].img,x,y, Tile.TILE_HEIGHT,Tile.TILE_WIDTH,null);
            }

            col++;
            x+=Tile.TILE_HEIGHT;
            if(col==25){
                col=0;
                x=0;
                row++;
                y+=Tile.TILE_WIDTH;
            }
        }
    }
}
