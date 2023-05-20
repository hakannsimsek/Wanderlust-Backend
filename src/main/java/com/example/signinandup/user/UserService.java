package com.example.signinandup.user;

import com.example.signinandup.registration.token.ConfirmationToken;
import com.example.signinandup.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "User with email %s not found";
    private final String EMAIL_ALREADY_TAKEN = "Email address %s is already taken";
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String singUpUser(User user) {
        boolean userExist = repository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExist){
            //TODO: if email not confirmed, send confirmation email

            throw new IllegalStateException(String.format(EMAIL_ALREADY_TAKEN, user.getEmail()));
        }


        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        repository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.SaveConfirmationToken(confirmationToken);

        //TODO: send email

        return token;
    }

    public int enableUser(String email) {
        return repository.enableUser(email);
    }
}
