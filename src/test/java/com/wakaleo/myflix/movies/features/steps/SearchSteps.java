package com.wakaleo.myflix.movies.features.steps;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jayway.restassured.RestAssured;
import com.wakaleo.myflix.movies.MovieServiceApplication;
import com.wakaleo.myflix.movies.features.serenitysteps.MovieCatalog;
import com.wakaleo.myflix.movies.model.Movie;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wakaleo.myflix.movies.features.steps.MovieComparators.byTitleAndDirector;
import static net.serenitybdd.rest.SerenityRest.rest;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SearchSteps {

    @Steps
    MovieCatalog theMovieCatalog;

    @Value("${local.server.port}")
    int port;

    List<Movie> matchingMovies;

    @Before
    public void configurePorts() {
        RestAssured.port = port;
    }

    @Given("the catalog has the following movies:")
    public void catalogMovies(DataTable movieData) {
        List<Map<String,String>> movieRows = movieData.asMaps(String.class, String.class);
        List<Movie> movies = movieRows.stream()
                                      .map(SearchSteps::convertStringDataToMovie)
                                      .collect(Collectors.toList());
        theMovieCatalog.hasTheFollowingMovies(movies);
    }

    private static Movie convertStringDataToMovie(Map<String, String> movieRow) {
        return new Movie(movieRow.get("title"),
                movieRow.get("description"),
                movieRow.get("director"),
                Splitter.on(",").omitEmptyStrings().trimResults().splitToList(movieRow.get("actors")));
    }

    @When("I search for movies directed by (.*)")
    public void searchByDirector(String director) {
        Movie[] movies = rest().when().get("/movies/findByDirector/" + director).as(Movie[].class);
        matchingMovies = ImmutableList.copyOf(movies);
    }

    @Then("I should be presented with the following movies:")
    public void shouldSeeMovies(List<Movie> expectedMovies) {
        assertThat(matchingMovies).usingElementComparator(byTitleAndDirector()).containsOnlyElementsOf(expectedMovies);
    }
}
