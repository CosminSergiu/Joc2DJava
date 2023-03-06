package PaooGame.State;

import PaooGame.Graphics.Assets;

import java.awt.*;

public class HelpState implements StateDraw{
    public void Draw(Graphics g){
        g.drawImage(Assets.help,0,0,null);
    }
}
