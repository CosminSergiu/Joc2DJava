package PaooGame.object;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class inima {


    public boolean collision ;
    public Rectangle solidArea = new Rectangle(0, 0, 35 , 39);
    public inima(){
        collision = true;
    }
    public void Draw(Graphics g, int x, int y){
        g.drawImage(Assets.inima, x, y, null);
    }
}
