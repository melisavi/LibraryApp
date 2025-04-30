package org.rog.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.common.dto.ApplicationUserDto;
import org.rog.library.core.dto.BookWithAuthorDto;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.mapper.BookMapper;
import org.rog.library.core.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ApplicationUserMapper mapper;
    private final BookMapper bookMapper;

    @PostMapping("/api/v1/user")
    public void saveUser(@RequestBody ApplicationUserDto applicationUserDto){
        userService.save(mapper.toEntity(applicationUserDto));
    }

    @GetMapping("/api/v1/user/books")
    List<BookWithAuthorDto> getAllBooksByUser() {
        return userService.findAllBooksByUser(getLoginFromContext()).stream().map(book -> bookMapper.toDtoWithAuthor(book)).collect(Collectors.toList());
    }

    @PostMapping("/api/v1/user/book/{id}")
    public void takeBookOn(@PathVariable(name = "id") Long id){
        userService.takeBookOn(id, getLoginFromContext());
    }

    @DeleteMapping("/api/v1/user/book/{id}")
    public void takeBookOff(@PathVariable(name = "id") Long id){
        userService.takeBookOff(id, getLoginFromContext());
    }

    private String getLoginFromContext(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.toString();
    }
}
