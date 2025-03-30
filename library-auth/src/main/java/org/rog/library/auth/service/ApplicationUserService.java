package org.rog.library.auth.service;

import org.rog.library.auth.dto.ApplicationUserDto;

public interface ApplicationUserService {
    void registerUser(ApplicationUserDto userDto);
}
