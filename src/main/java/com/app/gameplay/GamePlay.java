package com.app.gameplay;

import java.util.Scanner;

public interface GamePlay {

    int getNumberOfCurrentPlayers();
    void setUpGamePlay(Scanner scanner, int numberOfNewPlayers);
    void startGamePlay();
}
