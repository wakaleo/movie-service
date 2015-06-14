  Feature: Searching for movies
  In order to sell more movies
  As an online movie seller
  I want viewers to be able to find the movies they are interested in quickly and easily

  Scenario: Search movies by director
    Given the catalog has the following movies:
      | title                 | director       | actors                        |
      | Gladiator             | Ridley Scott   | Russel Crowe, Joaquin Phoenix |
      | Letters from Iwo Jima | Clint Eastwood | Ken Watanabe                  |
      | Gran Torino           | Clint Eastwood | Clint Eastwood, Bee Vang      |
    When I search for movies directed by Clint Eastwood
    Then I should be presented with the following movies:
      | title                 | director       | actors                   |
      | Letters from Iwo Jima | Clint Eastwood | Ken Watanabe             |
      | Gran Torino           | Clint Eastwood | Clint Eastwood, Bee Vang |
