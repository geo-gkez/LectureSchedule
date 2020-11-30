package com.company.schedule.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Table(name = "SCHEDULE_LECTURE")
@Entity(name = "schedule_Lecture")
@NamePattern("%s|topic")
public class Lecture extends StandardEntity {
    private static final long serialVersionUID = 1416373507555411933L;

    @NotNull
    @Column(name = "TOPIC", nullable = false)
    private String topic;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
    @FutureOrPresent
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "DURATION", nullable = false)
    @Positive
    private Integer duration;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Transient
    @MetaProperty(related = {"startDate", "duration"})
    public LocalDateTime getEndDate(){
            return (startDate != null && duration != null) ? startDate.plusHours(duration) : null;
    }
}