package com.company.model;

import com.company.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "habit")
public class Habit {

    public Habit() {
        //this.user = Utils.getCurrentUser();
    }

    public Habit(String title, User user, Integer per_day) {
        this.user = user;
        this.title = title;
        start = LocalDate.now();
        perDay = per_day;
    }

    @CreatedBy
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private OffsetDateTime created;

    private LocalDate start;

//    private Duration period; //1 time per period

    private Integer perDay;

    @Column(nullable = false, unique = false)
    private String title;

    @OneToMany(mappedBy = "habit")
    private Set<Event> events = new HashSet<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Integer getPerDay() {
        return perDay;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public LocalDate getStart() {
        return start;
    }

//    public Duration getPeriod() {
//        return period;
//    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPerDay(Integer perDay) {
        this.perDay = perDay;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

//

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public long getUserId() {return user.getId();}

    public Long getId() {
        return id;
    }

    public int countItemsPerDay(long period) {
        int secPerDay = 86400;
        int items = (int) period / secPerDay;
        return items;
    }
}