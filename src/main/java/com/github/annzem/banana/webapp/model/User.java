package com.github.annzem.banana.webapp.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "bananauser")
public class User {

    private transient static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        this.email = email;
        this.password = encodedPassword;
    }

    public User(String chatId) {
        this.chatId = chatId;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true, unique = false)
    private String name;

    @Column(nullable = true, unique = true)
    private String phone;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String chatId;

    @Column(nullable = true)
    private String password;

    @CreatedDate
    private OffsetDateTime created;

    @OneToMany(mappedBy = "user")
    private Set<Habit> habits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
