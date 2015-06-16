package com.wakaleo.myflix.movies.controllers

import com.wakaleo.myflix.movies.controllers.MovieController
import com.wakaleo.myflix.movies.repository.MovieRepository
import spock.lang.Specification
import spock.lang.Unroll


class MovieControllerSpecs extends Specification {

    MovieRepository movieRepository = Mock()

    @Unroll
    def "should find by director regardless of case (#director -> #filteredDirector)"() {
        given:
            def controller = new MovieController(repository: movieRepository)
        when:
            controller.findByDirector(director)
        then:
            1*movieRepository.findByDirector(filteredDirector)
        where:
             director          | filteredDirector
            "Clint Eastwood"   | "Clint Eastwood"
            "Clint eastwood"   | "Clint Eastwood"
            "clint eastwood"   | "Clint Eastwood"
            " clint eastwood " | "Clint Eastwood"
    }
}
