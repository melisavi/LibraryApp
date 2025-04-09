package org.rog.library.auth.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.dto.ApplicationFullUserDto;
import org.rog.library.auth.entity.ApplicationUserAccount;
import org.rog.library.auth.mapper.ApplicationUserAccountMapper;
import org.rog.library.auth.repository.ApplicationUserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserAccountMapper applicationUserAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserAccountRepository repository;

    @Override
    public void registerUser(ApplicationFullUserDto userDto) {
        ApplicationUserAccount applicationUserAccount = applicationUserAccountMapper.toEntity(userDto);
        applicationUserAccount.setPassword(passwordEncoder.encode(applicationUserAccount.getPassword()));
        repository.save(applicationUserAccount);
    }
}
