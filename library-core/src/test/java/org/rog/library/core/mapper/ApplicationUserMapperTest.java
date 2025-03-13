package org.rog.library.core.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.rog.library.core.dto.ApplicationUserDto;
import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.util.TestDataProvider;

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