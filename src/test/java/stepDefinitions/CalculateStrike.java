package stepDefinitions;

import com.app.display.GameDisplay;
import com.app.game.Game;
import com.app.model.Frame;
import com.app.model.Player;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CalculateStrike {

    BowlingAppHook hook = new BowlingAppHook();
    private Game game = BowlingAppHook.game;
    String firstName = BowlingAppHook.nameGenerator();
    String lastName = BowlingAppHook.nameGenerator();
    StringBuilder playerInput = new StringBuilder();
    private ByteArrayOutputStream outContent = BowlingAppHook.outContent;
    private ByteArrayOutputStream errContent = BowlingAppHook.errContent;
    private Player player = new Player(firstName,lastName);
    private ByteArrayInputStream inputStream;

    @Given("^I start a new game with a single player$")
    public void iStartANewGameWithASinglePlayer() throws Throwable {
        playerInput.append("1\n1\n2\n");
        playerInput.append(firstName);
        playerInput.append("\n");
        playerInput.append(lastName);
        playerInput.append("\nyes\n");
        playerInput.append("10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n");
        inputStream = new ByteArrayInputStream(playerInput.toString().getBytes());
        System.setIn(inputStream);
        game.startGame();
    }

    @Given("^I roll the bowling ball as my first roll on a \"([^\"]*)\"$")
    public void iRollTheBowlingBallAsMyFirstRollOnA(String frameNum) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertNotEquals( -1, aFrame.getFirstRoll());
        assertEquals(-1, aFrame.getSecondRoll());
    }


    @When("^I \"([^\"]*)\" ten pins for that \"([^\"]*)\"$")
    public void iTenPinsForThat(String hit, String frameNum) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertEquals(Integer.parseInt(hit), aFrame.getFirstRoll());
    }



    @Then("^the \"([^\"]*)\" \"([^\"]*)\" is ten plus the score of my next two rolls$")
    public void theIsTenPlusTheScoreOfMyNextTwoRolls(String frameNum, String score) throws Throwable {
        Frame aFrame = game.getGamePlay().getScore(player).getFrame(Integer.parseInt(frameNum));
        assertEquals(Integer.parseInt(score), aFrame.getTotalFrameScore());
    }
}
