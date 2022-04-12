package com.github.annzem.banana.webapp.dto;

import com.github.annzem.banana.webapp.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class HabitDto {

    private String title;
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    private int perDay;
//    private int todayEvents;

//    @OneToMany(mappedBy = "habit")
//    private Set<EventDto> events = new HashSet<>();

    private String icon;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPerDay() { return perDay; }

    public void setPerDay(int perDay) { this.perDay = perDay; }

//    public Set<EventDto> getEvents() {
//        return events;
//    }
    public String getIcon() { return icon; }

    public void setIcon(String icon) { this.icon = icon; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public int getTodayEvents() {
//        return todayEvents;
//    }
//
//    public void setTodayEvents(int todayEvents) {
//        this.todayEvents = todayEvents;
//    }
}
