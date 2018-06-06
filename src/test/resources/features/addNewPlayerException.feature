@addPlayer
Feature: Add new player exception
  Background:
    Given I have started a new game
    And I have selected the number of players i desire
    Then A request is made to add a new player

  Scenario: Add a new player
    When I enter the player name
    And  the player already exists in one of the player lists
    Then I should be informed that the player already exists
    And I should be given the option to go back to the previous menu

