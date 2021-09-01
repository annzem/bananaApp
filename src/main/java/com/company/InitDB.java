package com.company;

import com.company.model.Habit;
import com.company.model.User;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDB {

    @Autowired
    public InitDB(UserRepository userRepository, HabitRepository habitRepository) {
        if (userRepository.findAll().size() == 0) {
            User user1 = new User("Arnold", "pass1");
            User user2 = new User("Vasya", "pass2");
            User user3 = new User("Rosa", "pass3");
            userRepository.saveAndFlush(user1);
            userRepository.saveAndFlush(user2);
            userRepository.saveAndFlush(user3);
        }

        if (habitRepository.findAll().size() == 0) {
            User arnold = userRepository.findByUsername("Arnold");
            User vasya = userRepository.findByUsername("Vasya");
            User rosa = userRepository.findByUsername("Rosa");

            Habit habit1 = new Habit("swim", arnold);
            Habit habit2 = new Habit("banana", rosa);
            Habit habit3 = new Habit("pear", rosa);
            habitRepository.saveAndFlush(habit1);
            habitRepository.saveAndFlush(habit2);
            habitRepository.saveAndFlush(habit3);
        }
    }
}
