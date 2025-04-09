package org.rog.library.auth.config;

import org.mapstruct.factory.Mappers;
import org.rog.library.auth.mapper.ApplicationUserAccountMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public ApplicationUserAccountMapper applicationUserAccountMapper() {
        return Mappers.getMapper(ApplicationUserAccountMapper.class);
    }
}
