package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Boris extends Entity {
    Game gp;

    public Boris(Game gp) {
        this.gp = gp;
        solidArea = new Rectangle(4, 0, 20, 20);
        this.setDefaultValues();
    }

    private void setDefaultValues() {//initializam doar in constructor=>private
        x = 5 * 35;
        y = 1 * 39;
        speed = 1;
        direction = "";
    }

    public void Draw(Graphics g) {
        g.drawImage(Assets.boris, x, y, null);

    }

    public void Update() {
        if(x<22*35){
            x+=speed;
        }
        else if(x == 22*35){
            x = 5*35;
        }
    }
}