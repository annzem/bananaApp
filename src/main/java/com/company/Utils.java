package com.company;

import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
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
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        User user = userRepository.findById(principal.getUser().getId()).get();
        return Optional.of(user);
    }
}
