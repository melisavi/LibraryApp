package org.rog.library.auth.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.dto.LoginRequest;
import org.rog.library.auth.service.ApplicationUserService;
import org.rog.library.auth.service.AuthService;
import org.rog.library.common.dto.ApplicationUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;
    private final AuthService authService;

    @PostMapping("/api/v1/registration")
    public void registerUser(@RequestBody ApplicationUserDto userDto){
        applicationUserService.registerUser(userDto);
    }

    @PostMapping("/api/v1/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.authenticate(loginRequest);
    }
}
