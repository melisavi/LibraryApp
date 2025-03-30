package org.rog.library.auth.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.dto.ApplicationUserDto;
import org.rog.library.auth.mapper.ApplicationUserAccountMapper;
import org.rog.library.auth.mapper.ApplicationUserMapper;
import org.rog.library.common.entity.ApplicationUser;
import org.rog.library.common.entity.ApplicationUserAccount;
import org.rog.library.common.repository.ApplicationUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserAccountMapper applicationUserAccountMapper;
    private final ApplicationUserMapper applicationUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public void registerUser(ApplicationUserDto userDto) {
        ApplicationUserAccount applicationUserAccount = applicationUserAccountMapper.toEntity(userDto);
        applicationUserAccount.setPassword(passwordEncoder.encode(applicationUserAccount.getPassword()));
        ApplicationUser applicationUser = applicationUserMapper.toEntity(userDto);
        applicationUser.setApplicationUserAccount(applicationUserAccount);
        applicationUserRepository.save(applicationUser);
    }
}
