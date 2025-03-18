package org.rog.library.core.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.exception.ApplicationUserAlreadyExistsException;
import org.rog.library.core.repository.ApplicationUserAccountRepository;
import org.rog.library.core.entity.ApplicationUserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserAccountServiceImpl implements ApplicationUserAccountService, UserDetailsService {
    private final ApplicationUserAccountRepository applicationUserAccountRepository;
    private final PasswordEncoder encoder;

    @Override
    public void saveUser(ApplicationUserAccount user) {
        if(existsUserByLogin(user.getLogin())) {
            throw new ApplicationUserAlreadyExistsException(user.getLogin());
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
          applicationUserAccountRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        return applicationUserAccountRepository.findByLogin(login).orElseThrow(()-> new UsernameNotFoundException("User not found: %s".formatted(login)));
    }

    public boolean existsUserByLogin(String login) {
        if (!applicationUserAccountRepository.findByLogin(login).isEmpty()) {
            return true;
        }
        return false;
    }
}
