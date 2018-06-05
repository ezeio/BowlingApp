package com.app.gameplay.impl;

import com.app.gameplay.GamePlay;
import com.app.model.Player;
import java.util.Scanner;

public class TenPinGamePlay implements GamePlay {

    private Scanner scanner;
    private Player[] currentPlayers;


    @Override
    public int getNumberOfCurrentPlayers() {
        return currentPlayers.length;
    }

    public void setUpGamePlay(Scanner keyboardInput, int numberOfNewPlayers){
        scanner = keyboardInput;
        currentPlayers = new Player[numberOfNewPlayers];
        for(int i = 0; i < currentPlayers.length; i++ ){
            currentPlayers[i] = new Player();
        }
    }

    @Override
    public void startGamePlay() {

    }
}
