package com.company;

import com.company.dto.UserDto;
import com.company.model.User;
import com.company.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class UserController {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/new_user")
    public String newUserForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("localDate", LocalDate.now());
        return "new_user";
    }

    @PostMapping("/new_user")
    public String newUserResult(@ModelAttribute UserDto userDto, Model model) {
        model.addAttribute("userDto", userDto);
        User user = modelMapper.map(userDto, User.class);
        userRepository.saveAndFlush(user);
        model.addAttribute("localDate", LocalDate.now());
        return "new_user";
    }

}
