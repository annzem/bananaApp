package com.company.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "icon")
public class Icon {
    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name="habit_id", nullable=false)
    private Habit habit;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private OffsetDateTime created;

    private String enabledFilename;
    private String disabledFilename;
}
