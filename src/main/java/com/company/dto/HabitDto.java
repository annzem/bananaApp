package com.company.dto;

import com.company.model.Event;
import com.company.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

public class HabitDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    private String title;
    private int perDay;
    private Long id;

    @OneToMany(mappedBy = "habit")
    private Set<EventDto> events;

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

    public Set<EventDto> getEvents() {
        return events;
    }
    public String getIcon() { return icon; }

    public void setEvents(Set<EventDto> events) {
        this.events = events;
    }
    public void setIcon(String icon) { this.icon = icon; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
