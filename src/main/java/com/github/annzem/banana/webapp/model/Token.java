package com.github.annzem.banana.webapp.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "token")
public class Token {

    public Token() {
        tokenVal = UUID.randomUUID().toString();
    }

    public Token(User user) {
        tokenVal = UUID.randomUUID().toString();
        active = true;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tokenVal;

    @CreatedDate
    private OffsetDateTime created;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenVal() {
        return tokenVal;
    }

    public void setTokenVal(String tokenVal) {
        this.tokenVal = tokenVal;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
