package com.wakaleo.myflix.movies.health;

import com.google.common.collect.ImmutableList;
import com.wakaleo.myflix.movies.controllers.MovieController;
import com.wakaleo.myflix.movies.model.Movie;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieTransactionHealth implements HealthIndicator {

    @Autowired
    MovieController movieController;

    @Override
    public Health health() {
        return Health.status(transactionStatus()).build();
    }

    private Status transactionStatus() {
        try {
            Movie movie = new Movie("TEST title", "TEST description", "TEST director", ImmutableList.of("TEST actor"));
            Movie reloadedMovie = movieController.add(movie);
            movieController.delete(reloadedMovie.getId());
            return Status.UP;
        } catch (Throwable e) {
            return new Status("DOWN", e.toString());
        }
    }
}
