package org.rog.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.common.dto.ApplicationUserDto;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ApplicationUserMapper mapper;

    @PostMapping("/api/v1/user")
    public void saveUser(@RequestBody ApplicationUserDto applicationUserDto){
        userService.save(mapper.toEntity(applicationUserDto));
    }
}
