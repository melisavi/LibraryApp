package org.rog.library.core.mapper;

import org.mapstruct.Mapper;
import org.rog.library.core.dto.ApplicationUserDto;
import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.entity.ApplicationUserAccount;

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
