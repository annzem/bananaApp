package com.company;

import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private HabitRepository habitRepository;

    private UserRepository userRepository;

    @Autowired
    public MainController(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/habits")
    public String habits(
            @RequestParam(name = "name", required = false, defaultValue = "You have no habits") String name, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        User user = userRepository.findById(principal.getUser().getId()).get();
        model.addAttribute("user", user);
        return "habits";
    }
}

