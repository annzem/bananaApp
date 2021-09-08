package com.company;

import com.company.dto.HabitDto;
import com.company.model.Habit;
import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
public class MainController {

    private HabitRepository habitRepository;

    private UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    private ModelMapper modelMapper;

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
        User user = Utils.getCurrentUser();
        model.addAttribute("user", user);
        return "habits";
    }

    @GetMapping("/habit_redactor")
    public String habit_redactor(@RequestParam(name = "name", required = false, defaultValue = "You have no habits") String name, Model model) {
        User user = Utils.getCurrentUser();
        model.addAttribute("user", user);
        return "habit_redactor";
    }

    @GetMapping("/get_habits")
    public ResponseEntity<List<Habit>> getHabits() {
        return ResponseEntity.ok(habitRepository.findHabitsByUser(Utils.getCurrentUser()));
    }

    @GetMapping("/new_habit_page")
    public String newHabitForm(Model model) {
        model.addAttribute("habitDto", new HabitDto());
        return "new_habit_page";
    }

    @PostMapping("/new_habit_page")
    public String newHabitResult(@ModelAttribute HabitDto habitDto, Model model) {
        model.addAttribute("habitDto", habitDto);
        Habit habit = modelMapper.map(habitDto, Habit.class);
        habit.setUser(Utils.getCurrentUser());
        habit.setCreated(OffsetDateTime.now());
        habitRepository.saveAndFlush(habit);
        return "result";
    }
}

