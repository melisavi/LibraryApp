package org.rog.library.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class AuthServiceRequestFilter extends OncePerRequestFilter {
    private final String authServiceKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isEmpty(headerValue)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (headerValue.equals(authServiceKey)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("AuthService", "", Collections.emptySet());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
