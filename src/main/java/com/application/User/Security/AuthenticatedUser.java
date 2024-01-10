package com.application.User.Security;

import com.application.User.Entities.User;
import com.application.User.Repositories.UserRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<User> get() {
        return authenticationContext.getAuthenticatedUser(User.class)
                .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()).get());
    }

    public void logout() {
        authenticationContext.logout();
    }

}
