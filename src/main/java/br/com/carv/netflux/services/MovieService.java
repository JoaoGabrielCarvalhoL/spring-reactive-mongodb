package br.com.carv.netflux.services;

import br.com.carv.netflux.domain.Movie;
import br.com.carv.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();

    Flux<MovieEvent> streamMovieEvents(String id);
}
