Feature: Display artist details
  In order to find more films that I might like
  As a viewer
  I want to see all of the films a particular artist has worked on, either as an actor or as a director

  Scenario: Display an artist's filmography
    Given the catalog has the following movies:
      | title               | director     | actors                                   |
      | Gladiator           | Ridley Scott | Russel Crowe, Joaquin Phoenix            |
      | Zoolander           | Ben Stiller  | Ben Stiller, Owen Wilson                 |
      | Night at the Museum | Shawn Levy   | Ben Stiller, Carla Gugino, Ricky Gervais |
      | The Cable Guy       | Ben Stiller  | Jim Carrey                               |
    When I consult the filmography of Ben Stiller
    Then I should see the following films that he has acted in:
      | Zoolander           |
      | Night at the Museum |
    And I should see the following films that he has directed:
      | Zoolander     |
      | The Cable Guy |
