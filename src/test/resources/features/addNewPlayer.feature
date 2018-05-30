Feature:
  Background:
    Given I have selected a new game
    And I have selected number of players between one and four inclusive
    Then I am asked to add a new player

  Scenario: Add a new player
    When I enter the player name
    And  if the player does not exist in the player lists
    Then the player should be added to the list of players for the game
    And the player should be added to the list of existing players




