package com.wakaleo.myflix.movies;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.model.MovieNotFound;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

@RestController
@RequestMapping("/movies")
@Api(value = "/movies", description = "Movie catalog")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @RequestMapping(value = "/{id}", method = GET)
    @ApiOperation(value = "Find a specific movie", httpMethod = "GET")
    public Movie findById(@PathVariable String id) {
        Movie movie = repository.findOne(id);
        if (movie == null) {
            throw new MovieNotFound();
        }
        return movie;
    }

    @RequestMapping(value = "/findByDirector/{director}", method = GET)
    @ApiOperation(value = "Find movies from a given director",
            httpMethod = "GET",
            response = Movie.class,
            responseContainer = "List")
    public List<Movie> findByDirector(@PathVariable String director) {
        return repository.findByDirector(capitalizeFully(director.trim()));
    }

    @RequestMapping(method = GET)
    @ApiOperation("List all the movies in the catalog")
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "Add a new movie to the catalog", httpMethod = "POST")
    public Movie add(@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ApiOperation(value = "Removing a movie from the catalog", httpMethod = "DELETE")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

    Movie GLADIATOR = new Movie("Gladiator", "Sword and sandles", "Ridley Scott", ImmutableList.of("Russel Crowe", "Joaquin Phoenix"));
    Movie LETTERS_FROM_IWO_JIMA = new Movie("Letters from Iwo Jima", "The story of the battle of Iwo Jima...", "Clint Eastwood", ImmutableList.of("Ken Watanabe"));
    Movie GRAN_TORINO = new Movie("Gran Torino", "Disgruntled Korean War veteran", "Clint Eastwood", ImmutableList.of("Clint Eastwood", "Bee Vang"));

    @RequestMapping(value = "/admin/seed", method = GET)
    @ApiOperation(value = "Initialize the movie catalog", httpMethod = "GET")
    public String seedDatabase() {
        repository.save(ImmutableList.of(GLADIATOR, LETTERS_FROM_IWO_JIMA, GRAN_TORINO));
        return "DATABASE INITIALIZED";
    }

    @RequestMapping(value = "/admin/clean", method = GET)
    @ApiOperation(value = "Clear the movie catalog", httpMethod = "GET")
    public String cleanDatabase() {
        repository.deleteAll();
        return "DATABASE CLEANED";
    }

}
