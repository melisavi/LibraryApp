package org.rog.library.auth.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.dto.ApplicationUserDto;
import org.rog.library.auth.service.ApplicationUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @PostMapping("/api/v1/registration")
    void registerUser(@RequestBody ApplicationUserDto userDto){
        applicationUserService.registerUser(userDto);
    }

}
