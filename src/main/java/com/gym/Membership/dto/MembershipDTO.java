package com.gym.Membership.dto;

import com.gym.Membership.models.Person;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;




public class MembershipDTO {

    @Min(1)
    @Max(100)
    @NotNull
    private int number;

    @NotNull
    private Person person;

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
