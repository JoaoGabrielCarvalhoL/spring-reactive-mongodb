package br.com.carv.netflux.bootstrap;

import br.com.carv.netflux.domain.Movie;
import br.com.carv.netflux.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@SpringBootApplication
public class InitMovies implements CommandLineRunner {

    static {
        System.setProperty("spring.mongodb.embedded.version","5.0.0");
    }

    private final MovieRepository movieRepository;
    private final Logger logger = Logger.getLogger(InitMovies.class.getSimpleName());

    public InitMovies(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                .thenMany(Flux.just("Batman Begins", "Batman O Cavaleiro das Trevas",
                        "Batman O Cavaleiro das Trevas Ressurge"))
                .map(title -> new Movie(title))
                .flatMap(movieRepository::save)
                .subscribe(null, null, () -> {
                    movieRepository.findAll().subscribe(System.out::println);
                });

        movieRepository.save(new Movie("Garota Exemplar"));
    }
}
