package com.gym.Membership.controllers;

import com.gym.Membership.dto.MembershipDTO;
import com.gym.Membership.models.Membership;
import com.gym.Membership.service.MembershipsService;
import com.gym.Membership.util.MembershipsValidator;
import com.gym.Membership.util.MyException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gym.Membership.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/memberships")
public class MembershipsController {

    private final ModelMapper modelMapper;
    private final MembershipsService membershipsService;
    private final MembershipsValidator membershipsValidator;

    @Autowired
    public MembershipsController(ModelMapper modelMapper, MembershipsService membershipsService, MembershipsValidator membershipsValidator) {
        this.modelMapper = modelMapper;
        this.membershipsService = membershipsService;
        this.membershipsValidator = membershipsValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MembershipDTO membershipDTO,
                                          BindingResult bindingResult) {

        Membership membershipToAdd = convertToMembership(membershipDTO);

        membershipsValidator.validate(membershipToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            String errMsg = returnErrorsToClient(bindingResult);
            throw new MyException(errMsg);
        }

        membershipsService.add(membershipToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MembershipDTO>> getAll() {
        List<MembershipDTO> membershipsDTO = membershipsService.findAll().stream().map(this::convertToMembershipDTO)
                .collect(Collectors.toList());

        membershipsDTO.forEach(m -> m.getOwner().setMemberships(null));

        if (membershipsDTO.isEmpty()) {
            throw new MyException("There are no memberships yet");
        }

        return ResponseEntity.ok(membershipsDTO);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Optional<MembershipDTO>> getByNumber(@PathVariable("number") int number) {

        Optional<MembershipDTO> membershipDTO = membershipsService.findByNumber(number).stream().map(this::convertToMembershipDTO)
                .findAny();

        if (membershipDTO.isEmpty()) {
            throw new MyException("There are no Membership with this number");
        }

        return ResponseEntity.ok(membershipDTO);
    }


    private Membership convertToMembership(MembershipDTO membershipDTO) {
        return modelMapper.map(membershipDTO, Membership.class);
    }

    private MembershipDTO convertToMembershipDTO(Membership membership) {
        return getMembershipDTO(membership, modelMapper);
    }

    public static MembershipDTO getMembershipDTO(Membership membership, ModelMapper modelMapper) {
        MembershipDTO membershipDTO = modelMapper.map(membership, MembershipDTO.class);
        
        int daysLeft = membership.getRecording_day().getDayOfYear() + 30 - LocalDateTime.now().getDayOfYear();
        membershipDTO.setDaysLeft(daysLeft);
        
        String firstDay = membership.getRecording_day().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setFirstDay(firstDay);
        
        String lastDay = membership.getRecording_day().plusDays(30).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setLastDay(lastDay);

        return membershipDTO;
    }
}


















