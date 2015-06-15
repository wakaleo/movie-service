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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (actors != null ? !actors.equals(movie.actors) : movie.actors != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        return result;
    }
}
