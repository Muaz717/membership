package com.gym.Membership.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "Membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "number")
    @Min(1)
    @Max(100)
    @NotNull(message = "Number should not be 0")
    private int number;

    @Column(name = "recording_day")
    private LocalDateTime recording_day;

    @ManyToOne()
    @JoinColumn(name = "person", referencedColumnName = "name")
    private Person owner;

    public Membership() {}

    public Membership(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getRecording_day() {
        return recording_day;
    }

    public void setRecording_day(LocalDateTime recording_day) {
        this.recording_day = recording_day;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                "number=" + number +
                ", recording_day=" + recording_day +
                ", person=" + owner +
                '}';
    }
}
