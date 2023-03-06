package br.com.carv.netflux.repositories;

import br.com.carv.netflux.bootstrap.InitMovies;
import br.com.carv.netflux.domain.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@DataMongoTest
//@ExtendWith(SpringExtension.class)
@DisplayName("Movie Repository Test")
public class MovieRepositoryTest {

    static {
        System.setProperty("spring.mongodb.embedded.version","5.0.0");
    }

    @Autowired
    private MovieRepository movieRepository;
    private final Logger logger = Logger.getLogger(InitMovies.class.getSimpleName());

    @Test
    void save_persistMovie_whenSuccessful(){
        movieRepository.deleteAll()
                .thenMany(Flux.just("Batman Begins", "Batman O Cavaleiro das Trevas",
                        "Batman O Cavaleiro das Trevas Ressurge"))
                .map(title -> new Movie(title))
                .flatMap(movieRepository::save)
                .subscribe(null, null, () -> {
                    movieRepository.findAll().subscribe(System.out::println);
                });
    }
}
