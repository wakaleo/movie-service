package com.wakaleo.myflix.movies;

import com.wakaleo.myflix.movies.model.MovieNotFound;
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @RequestMapping("/movies/{id}")
    public Movie findById(@PathVariable String id) {
        Movie movie = repository.findOne(id);
        if (movie == null) {
            throw new MovieNotFound();
        }
        return movie;
    }

    @RequestMapping("/movies/findByDirector/{director}")
    public List<Movie> findByDirector(@PathVariable String director) {
        return repository.findByDirector(director);
    }

    @RequestMapping("/movies")
    public List<Movie> findAll() {
        return repository.findAll();
    }

}
