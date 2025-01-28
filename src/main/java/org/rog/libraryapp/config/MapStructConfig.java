package org.rog.libraryapp.config;

import org.mapstruct.factory.Mappers;
import org.rog.libraryapp.mapper.AuthorMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public AuthorMapper authorMapper() {
        return Mappers.getMapper(AuthorMapper.class);
    }

}
