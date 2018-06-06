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

public class AddANewPlayerException {
    private Game game = new TenPinGame();
    private GameDisplay gameDisplay = new TenPinGameDisplay();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n2\n2\nhenry\nozomena\n2\nhenry\nozomena\n".getBytes());

    @Given("^I have started a new game$")
    public void iHaveStartedANewGame() throws Throwable {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inputStream);
        game.setGameDisplay(gameDisplay);
        game.startGame();
    }

    @And("^I have selected the number of players i desire$")
    public void iHaveSelectedTheNumberOfPlayersIDesire() throws Throwable {
        assertEquals(2,game.getGamePlay().getNumberOfCurrentPlayers());
    }

    @Then("^A request is made to add a new player$")
    public void aRequestIsMadeToAddANewPlayer() throws Throwable {
        assertTrue(outContent.toString().contains("[2] new player"));
    }
    @When("^I enter the player name$")
    public void iEnterThePlayerName() throws Throwable {
        //Player name is already provided in the byte stream (for example henry ozomena)
    }

    @And("^the player already exists in one of the player lists$")
    public void thePlayerAlreadyExistsInOneOfThePlayerLists() throws Throwable {

        Player[] players = game.getGamePlay().getCurrentPlayers();
        assertTrue(players[0].equals(new Player("henry", "ozomena")));
    }

    @Then("^I should be informed that the player already exists$")
    public void iShouldBeInformedThatThePlayerAlreadyExists() throws Throwable {
        outContent.toString().contains("an existing player already has that first and last name combination");
    }

    @And("^I should be given the option to go back to the previous menu$")
    public void iShouldBeGivenTheOptionToGoBackToThePreviousMenu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       // throw new PendingException();
    }

}
