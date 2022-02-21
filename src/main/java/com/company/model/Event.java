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

    public Event (User user, Habit habit, int sort, boolean ticked) {
        this.habit = habit;
        this.user = user;
        this.ticked = ticked;
        this.sort = sort;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int sort;

    @ManyToOne
    @JoinColumn(name="habit_id", nullable=false)
    private Habit habit;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @CreatedDate
    private OffsetDateTime created;

    private Boolean ticked;

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

    public boolean isTicked() {return ticked;}

    public void setTicked(boolean ticked) { this.ticked = ticked; }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
