package com.wakaleo.myflix.movies.services

import com.wakaleo.myflix.movies.model.Movie
import com.wakaleo.myflix.movies.repository.MovieRepository
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class WhenAssemblingActorDetails extends Specification {

    static def LETTERS_FROM_IWO_JIMA = new Movie(title: "Letters from Iwo Jima", director: "Clint Eastwood",
            description: "The story of the battle of Iwo Jima...", actors: ["Ken Watanabe"]);
    static def THE_GOOD_THE_BAD_AND_THE_UGLY = new Movie(title: "The Good, the Bad and the Ugly", director: "Sergio Leone",
            description: " Spaghetti Western", actors: ["Clint Eastwood"]);
    static def GRAN_TORINO = new Movie(title: "Gran Torino", director: "Clint Eastwood",
            description: "Disgruntled Korean War veteran", actors: ["Clint Eastwood", "Bee Vang"]);

    MovieRepository repository
    def artistService

    def setup() {
        repository = Mock(MovieRepository)
        artistService = new ArtistService(movieRepository: repository)
    }

    def "should build actor details from films the artist has acted in and has directed"() {
        given: "Clint Eastwood has directed some films"
            repository.findByDirector("Clint Eastwood") >> [LETTERS_FROM_IWO_JIMA, GRAN_TORINO]
        and: "Clint Eastwood has starred in some films"
            repository.findByActors("Clint Eastwood") >> [THE_GOOD_THE_BAD_AND_THE_UGLY, GRAN_TORINO]
        when:
            def artistDetails = artistService.findArtistByName("Clint Eastwood")
        then:
            artistDetails.isPresent()
        and:
            artistDetails.get().name == "Clint Eastwood" &&
                    artistDetails.get().filmsActedIn == [THE_GOOD_THE_BAD_AND_THE_UGLY, GRAN_TORINO] &&
                    artistDetails.get().filmsDirected == [LETTERS_FROM_IWO_JIMA, GRAN_TORINO]
    }

    @Unroll
    def "should allow pure actors or directors"() {
        given:
            repository.findByDirector(artistName) >> artistDirected
        and:
            repository.findByActors(artistName) >> artistStarredIn
        when:
            def artistDetails = artistService.findArtistByName(artistName)
        then:
            artistDetails.isPresent() == detailsPresent
        where:
            artistName     | artistDirected                  | artistStarredIn | detailsPresent
            "Sergio Leone" | [THE_GOOD_THE_BAD_AND_THE_UGLY] | []              | true
            "Bee Vang"     | []                              | [GRAN_TORINO]   | true
            "Mozart"       | []                              | []              | false
    }
}
