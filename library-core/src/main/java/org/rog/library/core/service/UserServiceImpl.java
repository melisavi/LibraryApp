package org.rog.library.core.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ApplicationUserRepository repository;

    @Override
    public void save(ApplicationUser applicationUser) {
        repository.save(applicationUser);

    }
}
