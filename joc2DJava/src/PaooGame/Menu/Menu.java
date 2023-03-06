package PaooGame.Menu;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Level.Level1;
import PaooGame.Level.Level2;
import PaooGame.Nivel;
import PaooGame.Tiles.TileManager;
import PaooGame.state;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Menu extends MouseAdapter {
    public static Menu meniu;
    private Game gp;

    protected Menu(Game g) {
        this.gp = g;
    }

    public static Menu creareMeniu(Game g) {

        if (meniu == null) {
            meniu =new Menu(g);
        }
        return meniu;
    }

    public void Draw(Graphics g) {

        Font fnt = new Font("algerian", 1, 40);


        g.drawImage(Assets.menu, 0, 0, null);


        g.setFont(fnt);
        g.setColor(Color.BLACK);
        g.drawString("The U.R.S.S. II", 100, 300);

        g.drawString("START", 210, 440);
        g.drawRect(200, 400, 150, 50);

        g.drawString("LOAD", 210, 510);
        g.drawRect(200, 470, 150, 50);

        g.drawString("HELP", 210, 580);
        g.drawRect(200, 540, 150, 50);


    }

    public void update() {
        gp.wnd.GetCanvas().addMouseListener(this);
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

        if (mx > x && mx < (x + width)) {
            if (my > y && my < (y + height)) {
                return true;
            } else return false;
        } else return false;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my, 200, 400, 150, 50)) {
            if(gp.gameState == state.Victorie && gp.nivel == Nivel.Level2){
                gp.nivel = Nivel.Level1;
                gp.level = new Level1(gp);
            }
            gp.gameState = state.Game;
            gp.map.loadMap();
            gp.putin.setDefaultValues();
            gp.putin.direction = "";
            gp.putin.scor = 0;


        } else if (mouseOver(mx, my, 200, 470, 150, 50)) {

            gp.gameState = state.Game;

        } else if (mouseOver(mx, my, 200, 540, 150, 50)) {
            gp.gameState = state.Help;
        }
    }

    public void mouseReleased(MouseEvent e) {

    }
}
