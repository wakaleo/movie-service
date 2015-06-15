Feature: Adding new movies
  In order to sell more movies
  As an online movie seller
  I want to be able to add movies to the catalog

  Scenario: Adding movies
    Given the following movie has just come out
      | title          | director        | actors                           |
      | Jurassic World | Colin Trevorrow | Chris Pratt, Bryce Dallas Howard |
    When I add this movie to the catalog
    Then I should be able to find it in the catalog