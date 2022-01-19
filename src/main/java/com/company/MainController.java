package com.company;

import com.company.dto.HabitDto;
import com.company.dto.UserDto;
import com.company.dto.UserEditorDto;
import com.company.model.Habit;
import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@PreAuthorize("isAuthenticated()")
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

    @GetMapping("/")
    public RedirectView main() {
        return new RedirectView("/home");
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

    @GetMapping("/get_habits")
    public ResponseEntity<List<Habit>> getHabits() {
        return ResponseEntity.ok(habitRepository.findHabitsByUser(auditorAware.getCurrentAuditor().get()));
    }

    @GetMapping("/today")
    public String today(Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        return "dailyPlanScreen";
    }

    @GetMapping("/new_habit_page")
    public String newHabitForm(Model model) {
        model.addAttribute("habitDto", new HabitDto());
        model.addAttribute("localDate", LocalDate.now());
        return "new_habit_page";
    }

    @PostMapping("/new_habit_page")
    public RedirectView newHabitResult(@ModelAttribute HabitDto habitDto, Model model) {
        model.addAttribute("habitDto", habitDto);
        Habit habit = modelMapper.map(habitDto, Habit.class);
        habitRepository.saveAndFlush(habit);
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        return new RedirectView("/habits");
    }

    @GetMapping("/habit_editor/{id}")
    public String habitEditor(@PathVariable String id, Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        Habit habit = habitRepository.getById(Long.valueOf(id));
        model.addAttribute("habit", habit);
        return "habit_editor";
    }

    @PostMapping("/habit_editor/{id}")
    public RedirectView habitEditorRes(@ModelAttribute HabitDto habitDto, Model model, @PathVariable String id) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        Habit habit = habitRepository.getById(Long.valueOf(id));
        model.addAttribute("habit", habit);
        model.addAttribute("habitDto", habitDto);
        Habit habit2 = modelMapper.map(habitDto, Habit.class);
        habit.setTitle(habit2.getTitle());
        habit.setStart(habit2.getStart());
        habit.setPerDay(habit2.getPerDay());
        habitRepository.saveAndFlush(habit);
        return new RedirectView("/habits");
    }

    @PostMapping("/delete_habit/{id}")
    public ResponseEntity<String> deleteHabit(@ModelAttribute HabitDto habitDto, @PathVariable String id, Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        Habit habit = habitRepository.getById(Long.valueOf(id));
        habitRepository.delete(habit);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user_editor")
    public String userRedactorForm(Model model) {
        UserEditorDto userEditorDto = convertUserToUserEditorDto(auditorAware.getCurrentAuditor().get());


        model.addAttribute("userEditorDto", userEditorDto);
        return "user_editor";
    }

    @PostMapping(value = "/user_editor",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)

    public RedirectView userRedactorRes(@ModelAttribute UserDto userDto, Model model) {
        User currentUser = auditorAware.getCurrentAuditor().get();
        currentUser.setUsername(userDto.getUsername());

        userRepository.saveAndFlush(currentUser);
        return new RedirectView("/habits");
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setPassword("");
        return userDto;
    }

    private UserEditorDto convertUserToUserEditorDto(User user) {
        UserEditorDto userEditorDto = modelMapper.map(user, UserEditorDto.class);
        userEditorDto.setCurrentPassword("");
        return userEditorDto;
    }


}

