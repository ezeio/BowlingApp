@addPlayer
Feature: select existing player
  Background:
    Given a new player was added to the existing list

  Scenario: select existing player
    Given I have selected a new game and number of players
    And I am given the option to select existing players
    Given I am provided with a list of existing players
    When I select a player
    Then the player should be added to the current players list





