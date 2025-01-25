package com.gym.Membership.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.Membership.models.Person;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class MembershipDTO {

    @Min(1)
    @Max(100)
    @NotNull
    private int number;

    @NotNull
    private PersonDTO owner;

    @JsonIgnore
    private LocalDateTime recording_day;

    private String firstDay;

    private String lastDay;

    private int daysLeft;

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public LocalDateTime getRecording_day() {
        return recording_day;
    }

    public void setRecording_day(LocalDateTime recording_day) {
        this.recording_day = recording_day;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PersonDTO getOwner() {
        return owner;
    }

    public void setOwner(PersonDTO owner) {
        this.owner = owner;
    }
}
