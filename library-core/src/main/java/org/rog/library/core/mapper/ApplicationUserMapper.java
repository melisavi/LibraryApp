package org.rog.library.core.mapper;

import org.mapstruct.Mapper;
import org.rog.library.common.dto.ApplicationUserDto;
import org.rog.library.core.entity.ApplicationUser;

@Mapper
public interface ApplicationUserMapper {
    default ApplicationUser toEntity(ApplicationUserDto userDto){
        return ApplicationUser.builder()
                .login(userDto.getLogin())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .middleName(userDto.getMiddleName())
                .birthDate(userDto.getBirthDate())
                .gender(userDto.getGender())
                .build();
    };
}
