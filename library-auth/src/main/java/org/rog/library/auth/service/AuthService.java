package org.rog.library.auth.service;

import org.rog.library.auth.dto.LoginRequest;

public interface AuthService {
    String authenticate(LoginRequest loginRequest);
}
