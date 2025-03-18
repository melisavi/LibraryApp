package org.rog.library.core.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.dto.ApplicationUserDto;
import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.entity.ApplicationUserAccount;
import org.rog.library.core.mapper.ApplicationUserAccountMapper;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.repository.ApplicationUserRepository;
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
