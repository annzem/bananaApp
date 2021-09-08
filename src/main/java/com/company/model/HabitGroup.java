package com.company.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "habitGroup")
public class HabitGroup {
    public HabitGroup(String title, User user) {
        this.user = user;
        this.title = title;
        created = OffsetDateTime.now();
    }

    public HabitGroup() {
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private OffsetDateTime created;

    @Column(nullable = false, unique = true)
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
