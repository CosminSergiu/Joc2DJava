package PaooGame.Level;

import PaooGame.Game;
import PaooGame.state;

import java.awt.*;

public class Level2 implements Level{
    Game gp;
    public Level2(Game gp){
        this.gp = gp;
    }
    @Override
    public void Update() {
        if(gp.putin.scor ==70){
            gp.gameState = state.Victorie;
        }
    }

    @Override
    public void Draw(Graphics g) {
        gp.map.Draw(g);
        gp.drawScore((Graphics2D) g);
        gp.putin.Draw(g);
        gp.biden.Draw(g);
        gp.macron.Draw(g);
        gp.boris.Draw(g);
    }
}
