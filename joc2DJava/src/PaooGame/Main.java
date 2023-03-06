package PaooGame;

import PaooGame.GameWindow.GameWindow;

import java.io.IOException;

public class Main
{
    public static void main(String[] args){
        Game paooGame = new Game("PaooGame", 25*39, 25*35);
        paooGame.StartGame();
    }
}
