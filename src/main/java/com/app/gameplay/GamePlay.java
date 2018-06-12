package com.app.gameplay;

import com.app.model.TenPinGameScore;
import com.app.model.Player;
import java.util.Scanner;

public interface GamePlay {

    int getNumberOfCurrentPlayers();
    void setUpGamePlay(Scanner scanner, int numberOfPlayers);
    void startGamePlay();
    void setUpPlayers(int numberOfPlayers);
    Player[] getCurrentPlayers();
    void playerDetailsMenu();
    TenPinGameScore getScore(Player player);
    boolean isStartGameSelected();
}
