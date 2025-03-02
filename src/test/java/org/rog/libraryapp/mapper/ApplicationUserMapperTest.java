package org.rog.libraryapp.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.rog.libraryapp.dto.ApplicationUserDto;
import org.rog.libraryapp.entity.ApplicationUser;
import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.util.TestDataProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationUserMapperTest {
    private final ApplicationUserMapper mapper = Mappers.getMapper(ApplicationUserMapper.class);

    @Test
    void toEntity() {
        ApplicationUser user = TestDataProvider.buildApplicationUser();
        ApplicationUserDto userDto = mapper.toDto(user);
        ApplicationUser user2 = mapper.toEntity(userDto);
        assertEquals(userDto.getLogin(), user2.getLogin());
        assertEquals(userDto.getPassword(), user2.getPassword());
        assertTrue(user2.isAccountNonExpired());
        assertTrue(user2.isAccountNonLocked());
        assertTrue(user2.isCredentialsNonExpired());
        assertTrue(user2.isEnabled());
    }
}