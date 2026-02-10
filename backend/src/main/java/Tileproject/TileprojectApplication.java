package Tileproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("Tileproject")
@EnableJpaRepositories("Tileproject.repository")
@EntityScan("Tileproject.model")
public class TileprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TileprojectApplication.class, args);
    }
}
