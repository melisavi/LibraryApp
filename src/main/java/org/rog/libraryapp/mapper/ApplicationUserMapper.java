package org.rog.libraryapp.mapper;

import org.mapstruct.Mapper;
import org.rog.libraryapp.dto.ApplicationUserDto;
import org.rog.libraryapp.entity.ApplicationUser;

@Mapper
public interface ApplicationUserMapper {
    ApplicationUserDto toDto(ApplicationUser user);
    default ApplicationUser toEntity(ApplicationUserDto userDto){
        return ApplicationUser.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    };
}
