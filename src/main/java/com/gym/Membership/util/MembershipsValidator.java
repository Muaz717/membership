package com.gym.Membership.util;

import com.gym.Membership.models.Membership;
import com.gym.Membership.service.MembershipsService;
import com.gym.Membership.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class MembershipsValidator implements Validator {

    private final PeopleService peopleService;
    private final MembershipsService membershipsService;

    @Autowired
    public MembershipsValidator(PeopleService peopleService, MembershipsService membershipsService) {
        this.peopleService = peopleService;
        this.membershipsService = membershipsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Membership.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Membership membership = (Membership) target;

        if (membership.getOwner() == null) {
            return;
        }

        int daysLeft = membership.getRecording_day().getDayOfYear() + 30 - LocalDateTime.now().getDayOfYear();
        if (daysLeft <= 0) {
            errors.rejectValue("recording_day", "");
        }

        if (peopleService.findByName(membership.getOwner().getName()).isEmpty()) {
            errors.rejectValue("owner", "No registered owner with such name!");
        }

        if (membershipsService.findByNumber(membership.getNumber()).isPresent()) {
            errors.rejectValue("number", "Membership with such number already exists");
        }
    }
}
