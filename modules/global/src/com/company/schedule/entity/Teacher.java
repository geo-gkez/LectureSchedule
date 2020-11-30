package com.company.schedule.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Table(name = "SCHEDULE_TEACHER", indexes = {
        @Index(name = "IDX_SCHEDULE_TEACHER_LAST_NAME", columnList = "LAST_NAME")
})
@Entity(name = "schedule_Teacher")
@NamePattern("%s %s|firstName,lastName")
public class Teacher extends StandardEntity {
    private static final long serialVersionUID = -6541421753923450989L;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email
    private String email;

    @NotNull
    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}