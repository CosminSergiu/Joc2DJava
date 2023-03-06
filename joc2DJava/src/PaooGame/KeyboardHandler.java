package PaooGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
    public boolean [] poz = new boolean[5];
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
            poz[0] = true;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
            poz[1] = true;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
            poz[2] = true;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
            poz[3] = true;
        if(key == KeyEvent.VK_ESCAPE){
            poz[4] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
            poz[0] = false;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
            poz[1] = false;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
            poz[2] = false;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
            poz[3] = false;
        if(key == KeyEvent.VK_ESCAPE){
            poz[4] = false;
        }
    }
}
