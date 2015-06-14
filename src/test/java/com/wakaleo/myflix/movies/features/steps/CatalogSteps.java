package com.wakaleo.myflix.movies.features.steps;

import com.jayway.restassured.RestAssured;
import com.wakaleo.myflix.movies.MovieServiceApplication;
import com.wakaleo.myflix.movies.features.serenitysteps.MovieCatalog;
import com.wakaleo.myflix.movies.model.Movie;
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

import static com.wakaleo.myflix.movies.features.steps.MovieComparators.byTitleAndDirector;
import static net.serenitybdd.rest.SerenityRest.rest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CatalogSteps {

    @Steps
    MovieCatalog theMovieCatalog;

    @Value("${local.server.port}")
    int port;

    Movie newMovie;

    @Before
    public void configurePorts() {
        RestAssured.port = port;
    }

    @Given("the following movie has just come out")
    public void newMovie(List<Movie> movies) {
        newMovie = movies.get(0);
    }

    String movieId;

    @When("I add this movie to the catalog")
    public void addMovieToCatalog() {
        movieId = rest().given().contentType("application/json")
                .content(newMovie)
                .post("/movies")
                .then().statusCode(200)
                .extract().jsonPath().getString("id");
    }

    @Then("I should be able to find it in the catalog")
    public void shouldBeAbleToFindMovieInCatalog() {
        rest().given().contentType("application/json")
                .get("/movies/{movieId}", movieId)
                .then().statusCode(200)
                .and().body("title", equalTo(newMovie.getTitle()))
                .and().body("director", equalTo(newMovie.getDirector()));
    }

    @When("I remove '(.*)' from the catalog")
    public void removeMovieFromCatalog(String title) {
        movieId = theMovieCatalog.getIdForMovieWithTitle(title);
        rest().delete("/movies/{movieId}", movieId);
    }

    @Then("I should no longer be able to find it in the catalog")
    public void shouldNotBeAbleToFindMovieInCatalog() {
        rest().given().contentType("application/json")
                .get("/movies/{movieId}", movieId)
                .then().statusCode(404);
    }
}