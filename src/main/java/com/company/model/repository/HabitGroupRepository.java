package com.company.model.repository;

import com.company.model.Habit;
import com.company.model.HabitGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitGroupRepository extends JpaRepository<HabitGroup, Long> {
        HabitGroup findByTitle(String title);
}
