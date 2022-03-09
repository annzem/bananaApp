package com.company.model.repository;

import com.company.dto.ItemsQueryDto;
import com.company.model.Event;
import com.company.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findFirstEventByHabitAndSortAndCreatedBetweenOrderByCreatedDesc(Habit habit, int sort, OffsetDateTime yesterday, OffsetDateTime today);

//    @Query(value = "SELECT new com.company.dto.ItemsQueryDto(e.user_id, e.habit_id, e.sort, e.checked, e.icon) FROM Event e WHERE user_id = :user_id AND habit_id = :habit_id GROUP BY e.sort", nativeQuery = true)
//    List<List<ItemsQueryDto>> getItems(Long user_id, Long habit_id, OffsetDateTime yesterday, OffsetDateTime today);
}