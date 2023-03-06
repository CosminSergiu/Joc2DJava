package PaooGame.State;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class PierdereState implements StateDraw{
    public void Draw(Graphics g) {
        g.drawImage(Assets.pierdere,0,0,null);
    }

}
