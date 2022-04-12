package com.github.annzem.banana.webapp;

import com.github.annzem.banana.webapp.model.User;
import com.github.annzem.banana.webapp.model.repository.UserRepository;
import com.github.annzem.banana.webapp.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Utils {
    private static UserRepository userRepository;

    @Autowired
    public Utils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static Optional<User> getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        if (authentication.getPrincipal() instanceof MyUserPrincipal) {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
            User user = userRepository.findById(principal.getUser().getId()).get();
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
