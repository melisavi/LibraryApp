package org.rog.library.auth.service;

public interface JwtService {
    String buildJwt(String login);
}
