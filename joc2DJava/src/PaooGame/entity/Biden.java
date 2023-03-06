package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Biden extends Entity {
    Game gp;

    public Biden(Game gp) {
        this.gp = gp;
        solidArea = new Rectangle(4, 0, 20, 20);
        this.setDefaultValues();
    }

    private void setDefaultValues() {//initializam doar in constructor=>private
        x = 12 * 35;
        y = 12 * 39;
        speed = 1;
        direction = "";
    }

    public void Draw(Graphics g) {
        g.drawImage(Assets.biden, x, y, null);

    }

    public void Update() {
        if(y > 390){
            y-=speed;
        }
        else if(x > 210) {//
            x-=speed;
        }
        else if(x == 210){
            x = 595;
        }
    }

}


