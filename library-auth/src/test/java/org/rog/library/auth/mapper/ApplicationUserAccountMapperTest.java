package org.rog.library.auth.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.rog.library.auth.dto.ApplicationUserDto;
import org.rog.library.auth.util.TestDataProvider;
import org.rog.library.common.entity.ApplicationUserAccount;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationUserAccountMapperTest {
    private final ApplicationUserAccountMapper mapper = Mappers.getMapper(ApplicationUserAccountMapper.class);

    @Test
    void toEntity() {
        ApplicationUserAccount user = TestDataProvider.buildApplicationUser();
        ApplicationUserDto userDto = mapper.toDto(user);
        ApplicationUserAccount user2 = mapper.toEntity(userDto);
        assertEquals(userDto.getLogin(), user2.getLogin());
        assertEquals(userDto.getPassword(), user2.getPassword());
        assertTrue(user2.isAccountNonExpired());
        assertTrue(user2.isAccountNonLocked());
        assertTrue(user2.isCredentialsNonExpired());
        assertTrue(user2.isEnabled());
    }
}