package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.display.impl.TenPinGameDisplay;
import com.app.game.Game;
import com.app.game.impl.TenPinGame;
import com.app.model.Player;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddNewPlayer {


    private Game game = new TenPinGame();
    private GameDisplay gameDisplay = new TenPinGameDisplay();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n2\n2\nhenry\nozomena\n2\nchinasa\nnwafor\n".getBytes());

    @Given("^I have selected a new game$")
    public void iHaveSelectedANewGame() throws Throwable {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inputStream);
        game.setGameDisplay(gameDisplay);
        game.startGame();

    }

    @And("^I have selected number of players between one and four inclusive$")
    public void iHaveSelectedNumberOfPlayersBetweenOneAndFourInclusive() throws Throwable {
        assertEquals(2,game.getGamePlay().getNumberOfCurrentPlayers());
    }

    @Then("^I am asked to add a new player$")
    public void iAmAskedToAddANewPlayer() throws Throwable {
        assertTrue(outContent.toString().contains("[2] new player"));
    }

    @When("^I enter the player first and last name$")
    public void iEnterThePlayerFirstAndLastName() throws Throwable {
        Player player1 = game.getGamePlay().getCurrentPlayers()[0];
        Player player2 = game.getGamePlay().getCurrentPlayers()[1];
        assertTrue(player1.getFirstName().equalsIgnoreCase("henry"));
        assertTrue(player1.getLastName().equalsIgnoreCase("ozomena"));
        assertTrue(player2.getFirstName().equalsIgnoreCase("chinasa"));
        assertTrue(player2.getLastName().equalsIgnoreCase("nwafor"));
    }

    @And("^if the player does not exist in the player lists$")
    public void ifThePlayerDoesNotExistInThePlayerLists() throws Throwable {
    }

    @Then("^the player should be added to the list of players for the game$")
    public void thePlayerShouldBeAddedToTheListOfPlayersForTheGame() throws Throwable {
    }

    @And("^the player should be added to the list of existing players$")
    public void thePlayerShouldBeAddedToTheListOfExistingPlayers() throws Throwable {
    }
}
