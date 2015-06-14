package com.wakaleo.myflix.movies.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such movie")
public class MovieNotFound extends RuntimeException {
}
