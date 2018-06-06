package com.app.game.impl;

import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import com.app.game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TenPinGameTest {


    Game game;
    GameDisplay gameDisplay;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n4\n3\n1".getBytes());


    @Before
    public void setUp() throws Exception {
        gameDisplay = new TenPinGameDisplay();
        game = new TenPinGame();
        game.setGameDisplay(gameDisplay);
        System.setOut(new PrintStream(outContent));
        System.setIn(inputStream);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void showStartGame() {

        game.startGame();
        assertTrue(outContent.toString().contains("[1] Start new game"));
        assertTrue(outContent.toString().contains("[2] View player stats"));
        assertTrue(outContent.toString().contains("|select an option to proceed|"));
    }

    @Test
    public void selectNewGame() {
        game.startGame();
        game.selectNewGame();
        assertTrue(outContent.toString().contains("Give number of players between 1 and 4 inclusive"));
    }

}