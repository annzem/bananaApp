package com.company.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity(name = "icon")
public class Icon {
    public Icon() {
    }

    public Icon(String title, byte [] content, String enabledFilename, String disabledFilename) {
        this.title = title;
        this.content = content;
        this.enabledFilename = enabledFilename;
        this.disabledFilename = disabledFilename;
    }

    @Column(nullable = false, unique = true)
    private String title;

//    @ManyToOne
//    @JoinColumn(name="habit_id", nullable=false)
//    private Habit habit;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private OffsetDateTime created;

    @Lob
    byte[] content;

    private String enabledFilename;
    private String disabledFilename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getEnabledFilename() {
        return enabledFilename;
    }

    public void setEnabledFilename(String enabledFilename) {
        this.enabledFilename = enabledFilename;
    }

    public String getDisabledFilename() {
        return disabledFilename;
    }

    public void setDisabledFilename(String disabledFilename) {
        this.disabledFilename = disabledFilename;
    }
}
