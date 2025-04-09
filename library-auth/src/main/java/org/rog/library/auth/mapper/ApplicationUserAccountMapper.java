package org.rog.library.auth.mapper;

import org.mapstruct.Mapper;
import org.rog.library.auth.dto.ApplicationFullUserDto;
import org.rog.library.auth.entity.ApplicationUserAccount;

@Mapper
public interface ApplicationUserAccountMapper {
    ApplicationFullUserDto toDto(ApplicationUserAccount user);
    default ApplicationUserAccount toEntity(ApplicationFullUserDto userDto){
        return ApplicationUserAccount.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    };
}
