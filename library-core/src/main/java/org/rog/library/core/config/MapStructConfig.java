package org.rog.library.core.config;

import org.mapstruct.factory.Mappers;
import org.rog.library.core.mapper.ApplicationUserAccountMapper;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.mapper.AuthorMapper;
import org.rog.library.core.mapper.BookMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public AuthorMapper authorMapper() {
        return Mappers.getMapper(AuthorMapper.class);
    }

    @Bean
    public BookMapper bookMapper() {
        return Mappers.getMapper(BookMapper.class);
    }

    @Bean
    public ApplicationUserAccountMapper applicationUserAccountMapper() {
        return Mappers.getMapper(ApplicationUserAccountMapper.class);
    }

    @Bean
    public ApplicationUserMapper applicationUserMapper() {
        return Mappers.getMapper(ApplicationUserMapper.class);
    }
}
