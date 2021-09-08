package com.company.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="habit_id", nullable=false)
    private Habit habit;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @CreatedDate
    private OffsetDateTime created;

    private int number;
}
