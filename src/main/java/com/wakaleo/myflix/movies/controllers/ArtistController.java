package com.wakaleo.myflix.movies.controllers;

import com.wakaleo.myflix.movies.model.Artist;
import com.wakaleo.myflix.movies.model.UnknownArtist;
import com.wakaleo.myflix.movies.services.ArtistService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/artists")
@Api(value = "/artists", description = "Information about actors and directors")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/{name}", method = GET)
    @ApiOperation(value = "Find information about a given artist",
                  httpMethod = "GET",
                  response = Artist.class)
    public Artist findArtistDetails(@PathVariable String name) {
        return artistService.findArtistByName(name).orElseThrow(UnknownArtist::new);
    }

}
