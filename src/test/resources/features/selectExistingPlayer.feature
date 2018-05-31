Feature:
  Background:
    Given I have selected a new game
    And I have selected number of players between one and four inclusive
    Then I am asked to select existing player

  Scenario: Add a new player
    Given I am provided with a list of existing players
    When I select a player
    Then the player should be added to the list of players for the game





