package com.wakaleo.myflix.movies;

import com.jayway.restassured.RestAssured;
import com.wakaleo.myflix.MovieServiceApplication;
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
class WhenFindingMovies extends Specification {

    @Autowired
    MovieRepository movieRepository;

    @Value('${local.server.port}')
    int port;

    def setup() {
        movieRepository.deleteAll();

        def movies =[
            new Movie("Gladiator",
                    "Sword and sandles",
                    "Ridley Scott",
                      ["Russel Crowe","Joaquin Phoenix"]),
            new Movie("Letters from Iwo Jima",
                    "The story of the battle of Iwo Jima between the United States and Imperial Japan during World War II",
                    "Clint Eastwood",
                    ["Ken Watanabe"]),
            new Movie("Gran Torino",
                      "Disgruntled Korean War veteran Walt Kowalski sets out to reform his neighbor",
                      "Clint Eastwood",
                      ["Clint Eastwood", "Bee Vang"]),
        ]
        movieRepository.save(movies)

        RestAssured.port = port;

    }

    def "should list all movies"() {
        when:
            def movies = when().get("/movies").as(List)
        then:
            !movies.isEmpty()

    }

    def "should list movies by director"() {
        when:
            List<Movie> movies = when().get("/movies/findByDirector/Clint Eastwood").as(List)
        then:
            movies.collect {movie -> movie.title} == ["Letters from Iwo Jima", "Gran Torino"]

    }

}