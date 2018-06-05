package stepDefinitions;

import com.app.game.Game;
import com.app.game.impl.TenPinGame;
import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NewGame {

    private Game game = new TenPinGame();
    private GameDisplay gameDisplay = new TenPinGameDisplay();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n4".getBytes());

    @Given("^The user is ready to start a new game$")
    public void theUserIsReadyToStartANewGame() throws Throwable {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inputStream);
        game.setGameDisplay(gameDisplay);
        game.startGame();

    }

    @Given("^I select a new game$")
    public void iSelectANewGame() throws Throwable {
        //game.selectNewGame();
        //The method above is called when the first value of the byte stream is called (1)
    }

    @When("^I am asked for the number of players between one and four inclusive$")
    public void iAmAskedForTheNumberOfPlayersBetweenOneAndFourInclusive() throws Throwable {
        assertTrue(outContent.toString().contains("Give number of players between 1 and 4 inclusive"));
    }

    @Then("^I provide the number of players$")
    public void iProvideTheNumberOfPlayers() throws Throwable {
        //The second input in the byte stream (4) is provided to denote the provision of four players
    }

    @And("^a new game play is created for the number of players$")
    public void aNewGamePlayIsCreatedForTheNumberOfPlayers() throws Throwable {
        assertNotNull(game.getGamePlay());
        int numberOfPlayers = game.getGamePlay().getNumberOfCurrentPlayers();
        assertEquals(4, numberOfPlayers);
    }
}
