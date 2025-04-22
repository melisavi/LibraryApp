package org.rog.library.auth.service;

import org.rog.library.common.dto.ApplicationUserDto;

public interface ApplicationUserService {
    void registerUser(ApplicationUserDto userDto);
}
