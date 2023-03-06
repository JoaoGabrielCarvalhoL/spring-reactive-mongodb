package br.com.carv.netflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NetfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetfluxApplication.class, args);
	}

//	sudo systemctl start mongod

}
