package com.wakaleo.myflix.movies.health;

import com.wakaleo.myflix.movies.controllers.MovieController;
import com.wakaleo.myflix.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class CatalogHealth implements HealthIndicator {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Health health() {
        return (isCatalogStocked()) ?
                Health.status(new Status("UP", "Movie count: " + movieRepository.count())).build()
                : Health.down().build();
    }

    private boolean isCatalogStocked() {
        return (movieRepository.count() > 0);
    }
}