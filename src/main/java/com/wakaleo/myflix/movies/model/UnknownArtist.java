package com.wakaleo.myflix.movies.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such artist")
public class UnknownArtist extends RuntimeException {}
