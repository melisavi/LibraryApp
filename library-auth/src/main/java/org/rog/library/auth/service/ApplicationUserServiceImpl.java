package org.rog.library.auth.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.auth.entity.ApplicationUserAccount;
import org.rog.library.auth.mapper.ApplicationUserAccountMapper;
import org.rog.library.auth.repository.ApplicationUserAccountRepository;
import org.rog.library.common.dto.ApplicationUserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserAccountMapper applicationUserAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserAccountRepository repository;
    private final KafkaTemplate<String, ApplicationUserDto> kafkaTemplate;
    /**
     * Здесь необходимо применить паттерн OutboxTransaction.
     */
    @Override
    @Transactional
    public void registerUser(ApplicationUserDto userDto) {
        ApplicationUserAccount applicationUserAccount = applicationUserAccountMapper.toEntity(userDto);
        applicationUserAccount.setPassword(passwordEncoder.encode(applicationUserAccount.getPassword()));
        repository.save(applicationUserAccount);
        userDto.setLogin(null); //TODO: в приватный метод
        userDto.setPassword(null);
        kafkaTemplate.send("users", userDto);
    }
}
