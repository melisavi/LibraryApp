package org.rog.library.auth.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.repository.ApplicationUserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ApplicationUserAccountRepository applicationUserAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        return applicationUserAccountRepository.findByLogin(login).orElseThrow(()-> new UsernameNotFoundException("User not found: %s".formatted(login)));
    }
}
