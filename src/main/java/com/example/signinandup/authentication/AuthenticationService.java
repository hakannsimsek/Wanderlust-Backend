package com.example.signinandup.authentication;

import com.example.signinandup.config.JwtService;
import com.example.signinandup.user.User;
import com.example.signinandup.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(RegisterRequest registerRequest) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  registerRequest.getEmail(),
                  registerRequest.getPassword()
          )
        );
        var user = repository.findByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(registerRequest.getEmail() + " not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
