Feature: Player game statistics

  Background:
    Given I am at the main menu
    When I select player history
    Then I am shown a list of existing players and their game statistics


  Scenario:
    Given That each player has a saved game history
    Then the total number of balls bowled is shown for each game
    And the average frame score per game is shown for each game

