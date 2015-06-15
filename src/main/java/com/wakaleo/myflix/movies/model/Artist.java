package com.wakaleo.myflix.movies.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Artist {

    private String name;
    private List<Movie> filmsActedIn;
    private List<Movie> filmsDirected;

    public Artist() {
    }

    public Artist(String name, List<Movie> filmsActedIn, List<Movie> filmsDirected) {
        this.name = name;
        this.filmsActedIn = filmsActedIn;
        this.filmsDirected = filmsDirected;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getFilmsActedIn() {
        return filmsActedIn;
    }

    public List<Movie> getFilmsDirected() {
        return filmsDirected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActedIn(List<Movie> actedIn) {
        this.filmsActedIn = actedIn;
    }

    public void setDirected(List<Movie> directed) {
        this.filmsDirected = directed;
    }
}
