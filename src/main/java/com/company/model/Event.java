package com.company.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

//@NamedNativeQuery(name = "com.company.model.repository.EventRepository.findItems",
//        query = "SELECT p.first_name as first, p.last_name as last FROM Chess_Player p WHERE id = :id",
//        resultSetMapping = "Mapping.PlayerNameDto")
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "event")
public class Event {

    public Event () {}

    public Event (Habit habit, int sort, boolean ticked) {
        this.habit = habit;
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

    @CreatedDate
    private OffsetDateTime created;

    private Boolean ticked;

    public Long getId() {
        return id;
    }

    public Habit getHabit() {
        return habit;
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
