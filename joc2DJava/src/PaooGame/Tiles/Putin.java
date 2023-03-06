package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class Putin extends Tile{

    public Putin(int idd) {
        //super(Assets.putin, idd);

    }

    public void Update() {

    }
    public void Draw(Graphics g,int x,int y){
        g.drawImage(Assets.putin,x , y,TILE_WIDTH, TILE_HEIGHT,null);

    }
}
