package com.gym.Membership.repositories;

import com.gym.Membership.models.Membership;
import com.gym.Membership.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipsRepository extends JpaRepository<Membership, Integer> {
    Optional<Membership> findByNumber(int number);
}
