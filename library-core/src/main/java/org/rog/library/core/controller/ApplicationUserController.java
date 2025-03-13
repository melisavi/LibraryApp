package org.rog.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.service.ApplicationUserService;
import org.rog.library.core.dto.ApplicationUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;
    private final ApplicationUserMapper applicationUserMapper;

    @PostMapping("/api/v1/registration")
    void registerUser(@RequestBody ApplicationUserDto userDto){
        applicationUserService.saveUser(applicationUserMapper.toEntity(userDto));
    }

}
