package com.app;

import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import com.app.game.Game;
import com.app.game.impl.TenPinGame;

public class Main {

    public static void main (String[] args) {
        /*System.out.println(" ___________________________________________");
        System.out.println("|Henry O    |10|/ |");
        System.out.println("|           |_____|");
        System.out.println("|           |30   |");
        System.out.println("|___________|_____|_________________________");
        System.out.println("|Simba M    |*|   |");
        System.out.println("|           |_____|");
        System.out.println("|           |10   |");
        System.out.println("|___________|_____|_________________________");
        System.out.println("|Odili P    |3| / |");
        System.out.println("|           |_____|");
        System.out.println("|           |11   |");
        System.out.println("|___________|_____|_________________________");*/

        GameDisplay gameDisplay = new TenPinGameDisplay();
        Game game = new TenPinGame();
        game.setGameDisplay(gameDisplay);
        game.startGame();
    }
}

