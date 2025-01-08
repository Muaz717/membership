package com.gym.Membership.service;

import com.gym.Membership.models.Person;
import com.gym.Membership.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}








