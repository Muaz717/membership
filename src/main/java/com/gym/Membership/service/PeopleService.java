package com.gym.Membership.service;

import com.gym.Membership.models.Membership;
import com.gym.Membership.models.Person;
import com.gym.Membership.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public Optional<Person> findByName(String name) {
        Optional<Person> person = peopleRepository.findByName(name);

        return person;
    }

    @Transactional
    public Optional<Person> findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        return person;
    }

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }


    @Transactional
    public List<Membership> getMemsByPersonId(int id) {

        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getMemberships());

//            person.get().getMemberships().forEach(membership -> {
//
//            });

            return person.get().getMemberships();

        } else {
            return Collections.emptyList();
        }
    }

}








