package com.wakaleo.myflix.movies.features.serenitysteps;

import com.wakaleo.myflix.movies.MovieServiceApplication;
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@ContextConfiguration(loader = SpringApplicationContextLoader.class,
                      classes = MovieServiceApplication.class)
@WebAppConfiguration
public class MovieCatalog {

    @Autowired
    MovieRepository movieRepository;

    public void hasTheFollowingMovies(List<Movie> movies) {
        movieRepository.deleteAll();
        movieRepository.save(movies);
    }
}
