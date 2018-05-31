Feature: Calculate spare

  Scenario: I hit a strike
    Given I roll the bowling ball as my first roll on a frame
    When I hit ten pins
    Then add ten to the number of pins knocked down in my next two rolls.