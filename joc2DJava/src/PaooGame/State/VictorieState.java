package PaooGame.State;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class VictorieState implements StateDraw{
    public void Draw(Graphics g) {
        g.drawImage(Assets.victorie,0,0,null);
    }
}
