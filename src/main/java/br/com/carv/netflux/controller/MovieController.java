package br.com.carv.netflux.controller;


import br.com.carv.netflux.domain.Movie;
import br.com.carv.netflux.domain.MovieEvent;
import br.com.carv.netflux.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Movie> getMovieById(@PathVariable("id") String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.streamMovieEvents(id);
    }
}
