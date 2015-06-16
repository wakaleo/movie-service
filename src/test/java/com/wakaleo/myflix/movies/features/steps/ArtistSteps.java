package com.wakaleo.myflix.movies.features.steps;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.wakaleo.myflix.movies.MovieServiceApplication;
import com.wakaleo.myflix.movies.features.serenitysteps.MovieCatalog;
import com.wakaleo.myflix.movies.model.Movie;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
        classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ArtistSteps {

    @Steps
    MovieCatalog theMovieCatalog;

    @Value("${local.server.port}")
    int port;

    List<Movie> matchingMovies;

    @Before
    public void configurePorts() {
        RestAssured.port = port;
    }

    Response rest;
    String artist;

    RestAssuredResponseImpl restResponse;

    @When("I consult the filmography of (.*)")
    public void consultFilmographyOf(String artist) {
        this.artist = artist;
        rest().when().get("/artists/" + artist)
              .then().body("name", equalTo(artist));
    }

    @Then("I should see the following films that (?:he|she) has acted in:")
    public void shouldSeeFilmsActedIn(List<String> movieTitles) {
        then().body("filmsActedIn.title", equalTo(movieTitles));
    }

    @Then("I should see the following films that (?:he|she) has directed:")
    public void shouldSeeFilmsDirectedBy(List<String> movieTitles) {
        then().body("filmsDirected.title", equalTo(movieTitles));
    }
}
