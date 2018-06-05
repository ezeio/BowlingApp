package com.app.game;

import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import com.app.gameplay.GamePlay;

public interface Game {

    void startGame();

    void selectNewGame();

    GameDisplay getGameDisplay();
    void setGameDisplay(GameDisplay gameDisplay);

    GamePlay getGamePlay();
}
