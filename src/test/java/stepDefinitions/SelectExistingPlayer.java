package stepDefinitions;

import com.app.game.Game;
import com.app.model.Player;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SelectExistingPlayer {

    BowlingAppHook hook = new BowlingAppHook();
    private Game game = hook.game;
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayInputStream inputStream = BowlingAppHook.inputStream;

    @Given("^a new player was added to the existing list$")
    public void aNewPlayerWasAddedToTheExistingList() throws Throwable {
      inputStream = new ByteArrayInputStream("1\n1\n2\n2\nkanye\nwest\n2\nkelechi\nmaduka\n".getBytes());
      System.setIn(inputStream);
      game.startGame();
      //game.selectNewGame();
      //game.startGame();

      boolean isPlayerAdded = false;
        for (Player player : game.getGamePlay().getCurrentPlayers()) {
            if(player != null && player.equals(new Player("kanye", "west"))){
                isPlayerAdded = true;
            }
        }
      assertTrue(isPlayerAdded);
    }

    @Given("^I have selected a new game and number of players$")
    public void iHaveSelectedANewGameAndNumberOfPlayers() throws Throwable {
        //inputStream = new ByteArrayInputStream("1\n1\n".getBytes());
        //System.setIn(inputStream);
        //game.getGamePlay().playerDetailsMenu();
    }

    @And("^I am given the option to select existing players$")
    public void iAmGivenTheOptionToSelectExistingPlayers() throws Throwable {
        assertTrue(outContent.toString().contains("[1] use existing player"));
    }

    @Given("^I am provided with a list of existing players$")
    public void iAmProvidedWithAListOfExistingPlayers() throws Throwable {
        //assertTrue(outContent.toString().contains("existing player(s) menu"));
    }

    @When("^I select a player$")
    public void iSelectAPlayer() throws Throwable {
        //The first and only player in the list is selected (1: kanye west)
    }

    @Then("^the player should be added to the current players list$")
    public void thePlayerShouldBeAddedToTheCurrentPlayersList() throws Throwable {
        Player player1 = new Player("kanye", "west");
        assertTrue(game.getGamePlay().getCurrentPlayers()[0].equals(player1));
    }
}
