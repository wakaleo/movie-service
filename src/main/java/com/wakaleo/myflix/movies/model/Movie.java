package com.wakaleo.myflix.movies.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Movie {

    @Id
    private String id;
    private String title;
    private String description;
    private String director;
    private List<String> actors;

    public Movie() {}

    public Movie(String title, String description, String director, List<String> actors) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("title='").append(title).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
