Feature: Deleting movies
  In order to keep my catalog relevant
  As an online movie seller
  I want to be able to remove movies to the catalog

  Scenario: Deleting movies
    Given the catalog has the following movies:
      | title            | director        | actors         |
      | Batman and Robin | Joel Schumacher | George Clooney |
    When I remove 'Batman and Robin' from the catalog
    Then I should no longer be able to find it in the catalog