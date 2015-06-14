package com.wakaleo.myflix.movies.features.steps;

import com.wakaleo.myflix.movies.model.Movie;

import java.util.Comparator;

/**
 * Created by john on 14/06/2015.
 */
public class MovieComparators {
    public static Comparator<? super Movie> byTitleAndDirector() {
        return new Comparator<Movie>() {
            @Override
            public int compare(Movie movie1, Movie movie2) {
                int titleComparaison = movie1.getTitle().compareTo(movie2.getTitle());
                return (titleComparaison == 0) ? movie1.getDirector().compareTo(movie2.getDirector()) : titleComparaison;
            }
        };
    }
}
