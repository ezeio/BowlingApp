@strike
Feature: Calculate strike
  Background: setup game
    Given I start a new game with a single player


  Scenario Outline: I hit a strike
    Given I roll the bowling ball as my first roll on a "<frame>"
    When I "<hit>" ten pins for that "<frame>"
    Then the "<frame>" "<score>" is ten plus the score of my next two rolls

    Examples:
    |frame| hit   | score |
#    |  1  |  10   |  30   |
#    |  2  |  10   |  60   |
#    |  3  |  10   |  90   |
#    |  4  |  10   |  120  |
#    |  5  |  10   |  150  |
#    |  6  |  10   |  180  |
#    |  7  |  10   |  210  |
    |  8  |  10   |  240  |
    |  9  |  10   |  270  |
    |  10 |  10   |  300  |