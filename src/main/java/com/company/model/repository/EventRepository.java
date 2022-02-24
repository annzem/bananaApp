package com.company.model.repository;

import com.company.model.Event;
import com.company.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findTodayEventsByHabit(Habit habit);
    List<Event> findEventsByHabitAndSortOrderByCreated(Habit habit, int sort);
    Optional<Event> findFirstEventByHabitAndSortOrderByCreatedDesc(Habit habit, int sort);

}