package com.company.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "event")
public class Event {

    public Event () {}

    public Event (Habit habit, User user) {
        this.habit = habit;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="habit_id", nullable=false)
    private Habit habit;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @CreatedDate
    private OffsetDateTime created;

    public Long getId() {
        return id;
    }

    public Habit getHabit() {
        return habit;
    }

    public User getUser() {
        return user;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

}
