package com.wakaleo.myflix.movies.integration

import com.jayway.restassured.RestAssured
import com.wakaleo.myflix.movies.MovieServiceApplication
import com.wakaleo.myflix.movies.controllers.ArtistController
import com.wakaleo.myflix.movies.model.Artist
import com.wakaleo.myflix.movies.model.Movie
import com.wakaleo.myflix.movies.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.when

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
@IntegrationTest
class WhenViewingActorDetailsViaTheController extends Specification {

    def GLADIATOR = new Movie(title:"Gladiator", director:"Ridley Scott",
                              description:"Sword and sandles", actors:["Russel Crowe","Joaquin Phoenix"]);
    def LETTERS_FROM_IWO_JIMA = new Movie(title:"Letters from Iwo Jima", director:"Clint Eastwood",
                                          description:"The story of the battle of Iwo Jima...", actors:["Ken Watanabe"]);
    def THE_GOOD_THE_BAD_AND_THE_UGLY = new Movie(title:"The Good, the Bad and the Ugly", director:"Sergio Leone",
                                                  description:" Spaghetti Western", actors:["Clint Eastwood"]);
    def GRAN_TORINO = new Movie(title:"Gran Torino", director:"Clint Eastwood",
                                description:"Disgruntled Korean War veteran", actors:["Clint Eastwood", "Bee Vang"]);

    @Autowired
    ArtistController artistController;

    def "should retrieve actor details for an artist"() {
        given:
            movieCatalogContains([GLADIATOR, LETTERS_FROM_IWO_JIMA,
                                  THE_GOOD_THE_BAD_AND_THE_UGLY, GRAN_TORINO])
        when:
            Artist artist = artistController.findArtistDetails("Clint Eastwood")
        then:
            artist.name == "Clint Eastwood"
        and:
            artist.filmsActedIn.containsAll(THE_GOOD_THE_BAD_AND_THE_UGLY, GRAN_TORINO)
        and:
            artist.filmsDirected.containsAll(LETTERS_FROM_IWO_JIMA, GRAN_TORINO)
    }

    @Autowired
    MovieRepository movieRepository;

    def setup() {
        movieRepository.deleteAll();
    }

    def movieCatalogContains(List<Movie> movies) {
        movieRepository.save(movies)
    }


}