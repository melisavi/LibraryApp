package org.rog.libraryapp.controller;

import lombok.RequiredArgsConstructor;
import org.rog.libraryapp.dto.ApplicationUserDto;
import org.rog.libraryapp.mapper.ApplicationUserMapper;
import org.rog.libraryapp.service.ApplicationUserService;
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
