package org.rog.library.auth.mapper;

import org.mapstruct.Mapper;
import org.rog.library.auth.dto.ApplicationUserDto;
import org.rog.library.common.entity.ApplicationUserAccount;

@Mapper
public interface ApplicationUserAccountMapper {
    ApplicationUserDto toDto(ApplicationUserAccount user);
    default ApplicationUserAccount toEntity(ApplicationUserDto userDto){
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
