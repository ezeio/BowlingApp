Feature:
  Background:
    Given I have selected a new game
    And I have selected number of players between one and four inclusive
    Then I am asked to add a new player

  Scenario: Add a new player
    When I enter the player name
    And  the player already exists in one of the player lists
    Then I should be informed that the player already exists
    And I should be given the option to go back to the previous menu

