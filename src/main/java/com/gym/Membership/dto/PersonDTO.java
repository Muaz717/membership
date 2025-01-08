package com.gym.Membership.dto;


import com.gym.Membership.models.Membership;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PersonDTO {

    @NotEmpty
    @Size(min = 5, max = 100, message = "Name should be between 5 and 100 symbols")
    private String name;

    private List<Membership> memberships;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }
}
