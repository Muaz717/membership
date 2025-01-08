package com.gym.Membership.controllers;

import com.gym.Membership.dto.MembershipDTO;
import com.gym.Membership.models.Membership;
import com.gym.Membership.repositories.MembershipsRepository;
import com.gym.Membership.service.MembershipsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memberships")
public class MembershipsController {

    private final ModelMapper modelMapper;
    private final MembershipsService membershipsService;

    @Autowired
    public MembershipsController(ModelMapper modelMapper, MembershipsService membershipsService) {
        this.modelMapper = modelMapper;
        this.membershipsService = membershipsService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MembershipDTO membershipDTO,
                                          BindingResult bindingResult) {

        Membership membershipToAdd = convertToMembership(membershipDTO);

        if (bindingResult.hasErrors()) {

        }

        membershipsService.add(membershipToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{number}/addVisit")
    public ResponseEntity<HttpStatus> addVisit(@PathVariable("number")int number,
                                               BindingResult bindingResult) {

    }

    @GetMapping()
    public List<Membership> getAll() {
        return membershipsService.findAll();
    }

    @GetMapping("/{number}")
    public Optional<Membership> getByNumber(@PathVariable("number") int number) {
        return membershipsService.findByNumber(number);
    }

    private Membership convertToMembership(MembershipDTO membershipDTO) {
        return modelMapper.map(membershipDTO, Membership.class);
    }
}


















