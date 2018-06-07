
Feature: Add new player exception

  @addPlayer
  Scenario: Add a new player
    When I enter the player name
    And  the player already exists in one of the player lists
    Then I should be informed that the player already exists
    And I should be given the option to go back to the previous menu
  @addPlayer
  Scenario: Cancel add new player operation
    When I am asked for a name
    And  I input cancel instead of a valid name
    Then I should be taken back to the provide player name menu