package com.example.bookcollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.bookcollection.repository")
@EntityScan("com.example.bookcollection.model")
@SpringBootApplication
public class BookCollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCollectionApplication.class, args);
    }

}
