package com.app.gameplay;

import com.app.model.Player;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public interface GamePlay {

    int getNumberOfCurrentPlayers();
    void setUpGamePlay(Scanner scanner, int numberOfPlayers);
    void startGamePlay();
    void setUpPlayers(int numberOfPlayers);
    Player[] getCurrentPlayers();
}
