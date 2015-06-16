package com.wakaleo.myflix.movies.services;

import com.wakaleo.myflix.movies.model.Artist;
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArtistService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Artist> findArtistByName(String name) {
        List<Movie> actedIn = movieRepository.findByActors(name);
        List<Movie> directed = movieRepository.findByDirector(name);
        return (actedIn.isEmpty() && directed.isEmpty()) ?
                    Optional.empty() : Optional.of(new Artist(name, actedIn, directed));
    }
}
