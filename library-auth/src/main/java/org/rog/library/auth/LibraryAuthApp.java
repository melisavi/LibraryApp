package org.rog.library.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"org.rog.library.common.entity"})
@EnableJpaRepositories(basePackages = {"org.rog.library.common.repository"})
public class LibraryAuthApp {
    public static void main(String[] args) {
        SpringApplication.run(LibraryAuthApp.class, args);
    }
}
