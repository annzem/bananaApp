package com.github.annzem.banana.webapp.model.repository;

import com.github.annzem.banana.webapp.dto.ItemDbDto;
import com.github.annzem.banana.webapp.model.Event;
import com.github.annzem.banana.webapp.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findTodayEventsByHabit(Habit habit);

    Optional<Event> findFirstEventByHabitAndSortOrderByCreatedDesc(Habit habit, int sort);

    @Query(
            value = "SELECT e.*\n" +
                    "FROM event e\n" +
                    "         JOIN\n" +
                    "     (\n" +
                    "         SELECT sort, max(id) AS id_max\n" +
                    "         FROM Event e\n" +
                    "         WHERE habit_id = :habitId\n" +
                    "           AND created BETWEEN :from AND :to\n" +
                    "         GROUP BY sort\n" +
                    "     ) v ON v.id_max = e.id",
            nativeQuery = true)
    List<ItemDbDto> findItems(@Param("habitId") Long habitId,
                              @Param("from") LocalDate from,
                              @Param("to") LocalDate to);

}