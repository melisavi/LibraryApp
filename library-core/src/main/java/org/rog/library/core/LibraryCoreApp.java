package org.rog.library.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"org.rog.library.core.entity", "org.rog.library.common.entity"})
@EnableJpaRepositories(basePackages = {"org.rog.library.common.repository", "org.rog.library.core.repository"})
public class LibraryCoreApp {
    public static void main(String[] args){
        SpringApplication.run(LibraryCoreApp.class);
    }
}
