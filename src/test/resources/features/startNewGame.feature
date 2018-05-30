Feature: Main menu new game selection
  Background:
    Given The user is ready to start a new game

  Scenario: Starting a new game
    Given I select a new game
    When I am asked for the number of players between one and four inclusive
    Then I provide the number of players

