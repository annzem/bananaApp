package com.company.model;

import javax.persistence.*;

@Entity(name = "habit")
public class Habit {
    public Habit(String title, User user) {
        this.user = user;
        this.title = title;
    }

    public Habit() {
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}