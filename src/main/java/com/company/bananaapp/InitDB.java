package com.company.bananaapp;

import com.company.bananaapp.model.Event;
import com.company.bananaapp.model.Habit;
import com.company.bananaapp.model.User;
import com.company.bananaapp.model.repository.EventRepository;
import com.company.bananaapp.model.repository.HabitRepository;
import com.company.bananaapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDB {

    @Autowired
    public InitDB(UserRepository userRepository, HabitRepository habitRepository, EventRepository eventRepository) {
        eventRepository.deleteAll();
        habitRepository.deleteAll();
        userRepository.deleteAll();


        if (userRepository.findAll().size() == 0) {
            User user1 = new User("Arnold", "pass1");
            User user2 = new User("Vasya", "pass2");
            User user3 = new User("Rosa", "pass3");
            userRepository.saveAndFlush(user1);
            userRepository.saveAndFlush(user2);
            userRepository.saveAndFlush(user3);
        }

        User arnold = userRepository.findByUsername("Arnold");
        User vasya = userRepository.findByUsername("Vasya");
        User rosa = userRepository.findByUsername("Rosa");

        Habit habit1 = null, habit2 = null, habit3 = null;
        Habit habit4 = null; Habit habit5 = null; Habit habit6 = null;

        if (habitRepository.findAll().size() == 0) {
            habit1 = new Habit("vegetables", arnold, 6, "eggplant");
            habit2 = new Habit("fats", rosa, 4, "oil");
            habit3 = new Habit("fruits", arnold, 4, "apple");
            habit4 = new Habit("fruits", rosa, 5, "apple");
            habit5 = new Habit("cereals", rosa, 7, "grain");
            habit6 = new Habit("fruits", vasya, 4, "apple");
            habitRepository.saveAllAndFlush(Arrays.asList(habit1,habit2, habit3, habit4, habit5, habit6));
        }

        if (eventRepository.findAll().size() == 0) {
            Event event1 = new Event(habit1, 0, true);
            Event event2 = new Event(habit1, 1, true);
            Event event3 = new Event(habit2, 0, true);
            Event event4 = new Event(habit3, 0, true);
            Event event5 = new Event(habit4, 0, true);
            Event event6 = new Event(habit4, 1, true);
            Event event7 = new Event(habit5, 0, true);
            Event event8 = new Event(habit5, 1, true);
            eventRepository.saveAllAndFlush(Arrays.asList(event1,event2, event3, event4, event5, event6, event7, event8));
        }

        for (int i = 0; i < 10000; i++) {
            Event event1 = new Event(habit1, 0, true);
            Event event2 = new Event(habit1, 1, true);
            eventRepository.saveAllAndFlush(Arrays.asList(event1,event2));
        }
    }
}