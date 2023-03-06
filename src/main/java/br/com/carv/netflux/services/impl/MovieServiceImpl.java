package br.com.carv.netflux.services.impl;

import br.com.carv.netflux.domain.Movie;
import br.com.carv.netflux.domain.MovieEvent;
import br.com.carv.netflux.repositories.MovieRepository;
import br.com.carv.netflux.services.MovieService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final Logger logger = Logger.getLogger(MovieServiceImpl.class.getSimpleName());

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Mono<Movie> getMovieById(String id) {
        logger.info("Getting movie by id: " + id);
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        logger.info("Getting all movies");
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(id, new Date()));
        }).delayElements(Duration.ofSeconds(1));
    }
}
