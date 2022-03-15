package com.company.model.repository;

import com.company.model.Habit;
import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findHabitsByUser(User user);
    List<Habit> findHabitsByUserAndStartBefore(User user, LocalDate start);
}
