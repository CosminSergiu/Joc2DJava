package PaooGame.Level;

import PaooGame.Game;
import PaooGame.Nivel;
import PaooGame.state;

import java.awt.*;

public class Level1 implements Level{
    protected Game gp;
    public Level1(Game gp){
        this.gp = gp;
    }
    @Override
    public void Update() {
        if(gp.putin.scor == 50){
            gp.gameState = state.Victorie;
            gp.level = new Level2(gp);
            gp.nivel = Nivel.Level2;
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
