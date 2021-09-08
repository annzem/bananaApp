package com.company;

import com.company.model.Habit;
import com.company.model.HabitGroup;
import com.company.model.User;
import com.company.model.repository.HabitGroupRepository;
import com.company.model.repository.HabitRepository;
import com.company.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDB {

    @Autowired
    public InitDB(UserRepository userRepository, HabitRepository habitRepository, HabitGroupRepository habitGroupRepository) {
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

        if (habitRepository.findAll().size() == 0) {
            Habit habit1 = new Habit("goose", arnold);
            Habit habit2 = new Habit("snail", rosa);
            Habit habit3 = new Habit("snail", arnold);
            habitRepository.saveAndFlush(habit1);
            habitRepository.saveAndFlush(habit2);
            habitRepository.saveAndFlush(habit3);
        }

//        if (habitGroupRepository.findAll().size() == 0) {
//            HabitGroup food = new HabitGroup("food", rosa);
//            HabitGroup animals_rosa = new HabitGroup("animals", rosa);
//            HabitGroup animals_arnold = new HabitGroup("animals", arnold);
//
//            habitGroupRepository.saveAndFlush(animals_arnold);
//            habitGroupRepository.saveAndFlush(animals_rosa);
//            habitGroupRepository.saveAndFlush(food);
//        }
    }
}
