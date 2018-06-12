package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import com.app.game.Game;
import com.app.game.impl.TenPinGame;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class BowlingAppHook {
    public static ByteArrayOutputStream outContent;
    public static ByteArrayOutputStream errContent;
    public static ByteArrayInputStream inputStream;
    public static Game game;
    public static GameDisplay gameDisplay;

    @Before()
    public void beforeScenario(){
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        game = new TenPinGame();
        gameDisplay = new TenPinGameDisplay();
        game.setGameDisplay(gameDisplay);
    }

    public static String nameGenerator() {
        String nameChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder randomName = new StringBuilder();
        int charValues;
        while (randomName.length() < 9){
            charValues = random.nextInt(nameChar.length()) + 0 ;
            System.out.println("Index value " + (int)random.nextFloat() + charValues);
            randomName.append(nameChar.charAt(charValues));
        }
        return randomName.toString();
    }

    @After()
    public void afterScenario(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(System.in);
    }
}
