package com.wakaleo.myflix.movies;

import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.model.MovieNotFound;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

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
        return repository.findByDirector(capitalizeFully(director.trim()));
    }

    @RequestMapping("/movies")
    public List<Movie> findAll() {
        return repository.findAll();
    }

}
