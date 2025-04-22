package org.rog.library.core.kafka;

import lombok.RequiredArgsConstructor;
import org.rog.library.common.dto.ApplicationUserDto;
import org.rog.library.core.mapper.ApplicationUserMapper;
import org.rog.library.core.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//TODO: Handle deserialization exception (log with error level)
@Service
@RequiredArgsConstructor
public class ApplicationUserConsumer {
    private final UserService userService;
    private final ApplicationUserMapper mapper;

    @KafkaListener(topics = "users")
    public void consumeUser(@Payload ApplicationUserDto applicationUserDto){
        userService.save(mapper.toEntity(applicationUserDto));
    }
}
