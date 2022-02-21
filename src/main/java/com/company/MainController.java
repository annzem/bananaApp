package com.company;

import com.company.dto.HabitDto;
import com.company.dto.ItemDto;
import com.company.dto.UserDto;
import com.company.dto.UserEditorDto;
import com.company.model.Event;
import com.company.model.Habit;
import com.company.model.User;
import com.company.model.repository.EventRepository;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PreAuthorize("isAuthenticated()")
@Controller
public class MainController {

    private EventRepository eventRepository;
    private HabitRepository habitRepository;
    private UserRepository userRepository;
    private AuditorAware<User> auditorAware;
    private ModelMapper modelMapper;

    @Autowired
    public MainController(EventRepository eventRepository, HabitRepository habitRepository, UserRepository userRepository, AuditorAware<User> auditorAware, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
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
    public ResponseEntity<List<HabitDto>> getHabits() {
        List<Habit> habits = habitRepository.findHabitsByUser(auditorAware.getCurrentAuditor().get());
        List<HabitDto> habitDtos = new ArrayList<>();
        for (int i = 0; i < habits.size(); i++) {
            HabitDto habitDto = modelMapper.map(habits.get(i), HabitDto.class);
            habitDtos.add(habitDto);
        }
        return ResponseEntity.ok(habitDtos);
    }

    @GetMapping("/get_today_items")
    public ResponseEntity<List<List<ItemDto>>> getItems() {
        List<Habit> habits = habitRepository.findHabitsByUser(auditorAware.getCurrentAuditor().get());
        List<List<ItemDto>> items = new ArrayList<>();
        for (int i = 0; i < habits.size(); i++) {
            List<Event> events = eventRepository.findTodayEventsByHabit(habits.get(i));
            List<ItemDto> habitItems = new ArrayList<>();

            for (int j = 0; j < habits.get(i).getPerDay(); j++) {
                Optional<Event> lastEvent = eventRepository.findFirstEventByHabitAndSortOrderByCreatedDesc(habits.get(i), j);
                if (lastEvent.isPresent()) {
                    habitItems.add(new ItemDto(habits.get(i).getId(), j, lastEvent.get().isTicked(), habits.get(i).getIcon()));
                } else {
                    habitItems.add(new ItemDto(habits.get(i).getId(), j, false, habits.get(i).getIcon()));
                }
            }
            items.add(habitItems);
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/today")
    public String today(Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());

        return "today";
    }

    @PostMapping("/today/{habit_id}")
    public ResponseEntity<String> tickHabit(@PathVariable String habit_id, @ModelAttribute HabitDto habitDto,
                                            Model model) {
        model.addAttribute("user", auditorAware.getCurrentAuditor().get());
        Habit habit = habitRepository.getById(Long.valueOf(habit_id));
        //     Event event = new Event(habit, auditorAware.getCurrentAuditor().get());
        //     eventRepository.saveAndFlush(event);

        return ResponseEntity.ok().build();
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
        modelMapper.map(habitDto, habit);
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

