package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.KeyboardHandler;
import PaooGame.Tiles.Tile;
import PaooGame.state;

import java.awt.*;

public class Player extends Entity{
    Game gp;
    KeyboardHandler KeyH;

    public Player(Game gp, KeyboardHandler keyH){
        this.gp=gp;
        this.KeyH=keyH;
        solidArea = new Rectangle(5,5,20,25);
        setDefaultValues();


    }
    public void setDefaultValues(){//initializam doar in constructor=>private
        x=0;
        y=12*39;
        speed=2;
    }

    public void Update() {

        gp.wnd.GetCanvas().addKeyListener(KeyH);
            if (KeyH.poz[0]) {
                direction ="up";
                //y -= speed;
            }

            if (KeyH.poz[1]) {
                direction ="left";
                //x -= speed;
            }
            if (KeyH.poz[2]) {
                direction ="down";
                //y += speed;
            }
            if (KeyH.poz[3]) {
                direction ="right";
                //x += speed;
            }

            collisionOn=false;
            gp.cCollision.checkTile(this);

            if(!collisionOn){
                switch (direction){
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }
            if((this.x == gp.biden.x && this.y == gp.biden.y) || (this.x == gp.macron.x && this.y == gp.biden.y )|| (this.x == gp.boris.x&& this.y== gp.biden.y)){//lucram la o varianta mai serioasa de pierdere
                //asteptam varianta 2.0


                gp.gameState = state.Pierdere;
            }
    }

    public void Draw(Graphics g){
        g.drawImage(Assets.putin, x, y, null);

    }
}
