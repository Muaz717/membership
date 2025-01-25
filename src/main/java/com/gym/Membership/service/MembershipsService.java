package com.gym.Membership.service;

import com.gym.Membership.models.Membership;
import com.gym.Membership.models.Person;
import com.gym.Membership.repositories.MembershipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipsService {

    private final PeopleService peopleService;
    private final MembershipsRepository membershipsRepository;

    @Autowired
    public MembershipsService(PeopleService peopleService, MembershipsRepository membershipsRepository) {
        this.peopleService = peopleService;
        this.membershipsRepository = membershipsRepository;
    }

    @Transactional
    public List<Membership> findAll() {
        return membershipsRepository.findAll();
    }

    @Transactional
    public Optional<Membership> findByNumber(int number) {

        Optional<Membership> membership = membershipsRepository.findByNumber(number);

        return membership;
    }

    @Transactional
    public void add(Membership membership) {
        enrichMembership(membership);

        membershipsRepository.save(membership);
    }

    private void enrichMembership(Membership membership) {
        membership.setOwner(peopleService.findByName(membership.getOwner().getName()).get());

        membership.setRecording_day(LocalDateTime.now());

    }


}
