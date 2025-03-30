package org.rog.library.auth.mapper;

import org.mapstruct.Mapper;
import org.rog.library.auth.dto.ApplicationUserDto;
import org.rog.library.common.entity.ApplicationUser;

@Mapper
public interface ApplicationUserMapper {
    default ApplicationUser toEntity(ApplicationUserDto userDto){
        return ApplicationUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .middleName(userDto.getMiddleName())
                .birthDate(userDto.getBirthDate())
                .gender(userDto.getGender())
                .build();
    };
}
