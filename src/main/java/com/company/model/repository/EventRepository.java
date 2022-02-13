package com.company.model.repository;

import com.company.model.Event;
import com.company.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findTodayEventsByHabit(Habit habit);
}