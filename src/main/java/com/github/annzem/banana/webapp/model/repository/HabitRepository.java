package com.github.annzem.banana.webapp.model.repository;

import com.github.annzem.banana.webapp.model.Habit;
import com.github.annzem.banana.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findHabitsByUser(User user);
    List<Habit> findHabitsByUserAndStartBefore(User user, LocalDate start);
}
