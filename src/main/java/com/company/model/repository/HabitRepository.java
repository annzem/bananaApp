package com.company.model.repository;

import com.company.model.Habit;
import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    Habit findByTitle(String title);
    List<Habit> findHabitsByUser(User user);
}
