package com.company.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "bananauser")
public class User {

    private transient static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        this.username = username;
        this.password = encodedPassword;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Habit> habits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String encodedPassword = passwordEncoder.encode(password);
        this.password = encodedPassword;
    }

    public Set<Habit> getHabits() {
        return habits;
    }
}
