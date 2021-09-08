package com.company.dto;

import com.company.Utils;
import com.company.model.Habit;
import com.company.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class HabitDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    private String title;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
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


}
