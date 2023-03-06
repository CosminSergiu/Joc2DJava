package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Macron extends Entity {
    Game gp;

    public Macron(Game gp) {
        this.gp = gp;
        solidArea = new Rectangle(4, 0, 20, 20);
        this.setDefaultValues();
    }

    private void setDefaultValues() {//initializam doar in constructor=>private
        x = 6 * 35;
        y = 19 * 39;
        speed = 1;
        direction = "";
    }

    public void Draw(Graphics g) {
        g.drawImage(Assets.macron, x, y, null);

    }

    public void Update() {
        if(x<22*35){
            x+=speed;
        }
        else if(x == 22*35){
            x = 6*35;
        }

    }
}

