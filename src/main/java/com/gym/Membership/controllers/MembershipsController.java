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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping()
    public List<MembershipDTO> getAll() {
        return membershipsService.findAll().stream().map(this::convertToMembershipDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{number}")
    public Optional<MembershipDTO> getByNumber(@PathVariable("number") int number) {
        return membershipsService.findByNumber(number).stream().map(this::convertToMembershipDTO)
                .findAny();
    }

    private Membership convertToMembership(MembershipDTO membershipDTO) {
        return modelMapper.map(membershipDTO, Membership.class);
    }

    private MembershipDTO convertToMembershipDTO(Membership membership) {
        MembershipDTO membershipDTO = modelMapper.map(membership, MembershipDTO.class);

        int daysLeft = membershipDTO.getRecording_day().getDayOfYear() + 30 - LocalDateTime.now().getDayOfYear();
        membershipDTO.setDaysLeft(daysLeft);


        LocalDateTime lastDay = membershipDTO.getRecording_day().plusDays(30);
        membershipDTO.setLastDay(lastDay);

        return membershipDTO;
    }
}


















