package com.gym.Membership.util;

import com.gym.Membership.models.Person;
import com.gym.Membership.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "Person with such name already exists!");
        }
    }
}
