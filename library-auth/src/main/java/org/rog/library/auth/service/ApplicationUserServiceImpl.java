package org.rog.library.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.rog.library.auth.dto.ApplicationFullUserDto;
import org.rog.library.auth.entity.ApplicationUserAccount;
import org.rog.library.auth.mapper.ApplicationUserAccountMapper;
import org.rog.library.auth.repository.ApplicationUserAccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    private final ApplicationUserAccountMapper applicationUserAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserAccountRepository repository;
    private final RestTemplate restTemplate;
    @Value("${authService.secret}")
    private String authServiceKey;
    @Value("${libraryCore.url}")
    private String libraryCoreURL;

    /**
     * Здесь необходимо применить паттерн OutboxTransaction.
     */
    @Override
    @Transactional
    public void registerUser(ApplicationFullUserDto userDto) {
        ApplicationUserAccount applicationUserAccount = applicationUserAccountMapper.toEntity(userDto);
        applicationUserAccount.setPassword(passwordEncoder.encode(applicationUserAccount.getPassword()));
        repository.save(applicationUserAccount);
        userDto.setLogin(null); //TODO: в приватный метод
        userDto.setPassword(null);
        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.AUTHORIZATION, authServiceKey);
        HttpEntity<ApplicationFullUserDto> httpEntity = new HttpEntity<>(userDto, headers);
        restTemplate.exchange(libraryCoreURL, HttpMethod.POST, httpEntity, Void.class);//TODO: url вынести в конфиги
    }
}
