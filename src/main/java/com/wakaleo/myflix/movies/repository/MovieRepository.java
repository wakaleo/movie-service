package com.wakaleo.myflix.movies.repository;

import com.wakaleo.myflix.movies.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    public List<Movie> findByDirector(String director);
//    public List<Movie> findByActor(String actor);
//    public List<Movie> findByKeyword(String keyword);

}