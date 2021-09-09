package com.company;

import com.company.dto.HabitDto;
import com.company.model.Habit;
import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    private HabitRepository habitRepository;
    private UserRepository userRepository;
    private AuditorAware<User> auditorAware;
    private ModelMapper modelMapper;

    @Autowired
    public MainController(HabitRepository habitRepository, UserRepository userRepository, AuditorAware<User> auditorAware, ModelMapper modelMapper) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
        this.auditorAware = auditorAware;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/habits")
    public String habits(Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        return "habits";
    }

    @GetMapping("/habit_redactor")
    public String habit_redactor(Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        return "habit_redactor";
    }

    @GetMapping("/get_habits")
    public ResponseEntity<List<Habit>> getHabits() {
        return ResponseEntity.ok(habitRepository.findHabitsByUser(auditorAware.getCurrentAuditor().get()));
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
        habitRepository.saveAndFlush(habit);
        return "result";
    }

    @GetMapping("/")
    public RedirectView main() {
        return new RedirectView("/home");
    }
}

