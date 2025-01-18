package com.gym.Membership.dto;

import com.gym.Membership.models.Person;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class MembershipDTO {

    @Min(1)
    @Max(100)
    @NotNull
    private int number;

    @NotNull
    private Person person;

    private LocalDateTime recording_day;

    private LocalDateTime lastDay;

    private int daysLeft;


    public LocalDateTime getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDateTime lastDay) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
