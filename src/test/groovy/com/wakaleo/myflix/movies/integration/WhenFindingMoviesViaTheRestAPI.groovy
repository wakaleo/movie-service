package com.wakaleo.myflix.movies.integration;

import com.jayway.restassured.RestAssured
import com.wakaleo.myflix.movies.MovieServiceApplication
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.when;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
class WhenFindingMoviesViaTheRestAPI extends Specification {

    @Autowired
    MovieRepository movieRepository;

    @Value('${local.server.port}')
    int port;

    def GLADIATOR = new Movie(title:"Gladiator", director:"Ridley Scott",
                              description:"Sword and sandles", actors:["Russel Crowe","Joaquin Phoenix"]);
    def LETTERS_FROM_IWO_JIMA = new Movie(title:"Letters from Iwo Jima", director:"Clint Eastwood",
                                          description:"The story of the battle of Iwo Jima...", actors:["Ken Watanabe"]);
    def GRAN_TORINO = new Movie(title:"Gran Torino", director:"Clint Eastwood",
                                description:"Disgruntled Korean War veteran", actors:[["Clint Eastwood", "Bee Vang"]]);

    def setup() {
        movieRepository.deleteAll();
        RestAssured.port = port;
    }

    def "should list all movies"() {
        given:
            movieCatalogContains([GLADIATOR, LETTERS_FROM_IWO_JIMA, GRAN_TORINO])
        when:
            def movies = when().get("/movies").as(List)
        then:
            !movies.isEmpty()
    }

    def "should return empty list if the catalog is empty"() {
        given:
            movieCatalogContains([])
        when:
            def movies = when().get("/movies").as(List)
        then:
            movies.isEmpty()
    }

    def "should list movies by director"() {
        given:
            movieCatalogContains([GLADIATOR, LETTERS_FROM_IWO_JIMA, GRAN_TORINO])
        when:
            List<Movie> movies = when().get("/movies/findByDirector/Clint Eastwood").as(List)
        then:
            movies.collect {movie -> movie.title} == ["Letters from Iwo Jima", "Gran Torino"]
    }

    def "should return empty list if no matching films found"() {
        given:
            movieCatalogContains([GLADIATOR, LETTERS_FROM_IWO_JIMA, GRAN_TORINO])
        when:
            List<Movie> movies = when().get("/movies/findByDirector/Peter Jackson").as(List)
        then:
            movies.isEmpty()
    }

    def movieCatalogContains(List<Movie> movies) {
        movieRepository.save(movies)
    }


}