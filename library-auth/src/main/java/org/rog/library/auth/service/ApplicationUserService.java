package org.rog.library.auth.service;

import org.rog.library.auth.dto.ApplicationFullUserDto;

public interface ApplicationUserService {
    void registerUser(ApplicationFullUserDto userDto);
}
