package com.gym.Membership.controllers;


import com.gym.Membership.dto.MembershipDTO;
import com.gym.Membership.dto.PersonDTO;
import com.gym.Membership.models.Membership;
import com.gym.Membership.models.Person;
import com.gym.Membership.service.PeopleService;
import com.gym.Membership.util.MyException;
import com.gym.Membership.util.PeopleValidator;
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

import static com.gym.Membership.controllers.MembershipsController.getMembershipDTO;
import static com.gym.Membership.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    private final PeopleValidator peopleValidator;

    @Autowired
    public PeopleController(PeopleService peopleService , ModelMapper modelMapper, PeopleValidator peopleValidator) {
        this.modelMapper = modelMapper;
        this.peopleService = peopleService;
        this.peopleValidator = peopleValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid PersonDTO personDTO,
                                          BindingResult bindingResult) {

        Person personToAdd = convertToPerson(personDTO);

        peopleValidator.validate(personToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
           String errMsg = returnErrorsToClient(bindingResult);

           throw new MyException(errMsg);
        }

        peopleService.register(personToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<PersonDTO> getAll() {

        List<PersonDTO> peopleDTO = peopleService.findAll().stream().map(this::convertToPersonDTO)
                .toList();

        peopleDTO.forEach(p -> p.getMemberships().forEach(m -> m.setOwner(null)));
        peopleDTO.forEach(p -> p.getMemberships().forEach(this::enrichMembershipDTO));

        return peopleDTO;
    }

    @GetMapping("/{id}")
    public List<MembershipDTO> getMemsByPersonId(@PathVariable("id") int id) {

        List<MembershipDTO> membershipsDTO = peopleService.getMemsByPersonId(id).stream().map(this::convertToMembershipDTO).toList();

        membershipsDTO.forEach(m -> m.setOwner(null));
        membershipsDTO.forEach(this::enrichMembershipDTO);

        return membershipsDTO;
    }


    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private MembershipDTO convertToMembershipDTO(Membership membership) {
        return getMembershipDTO(membership, modelMapper);
    }

    private void enrichMembershipDTO(MembershipDTO membershipDTO) {
        
        int daysLeft = membershipDTO.getRecording_day().getDayOfYear() + 30 - LocalDateTime.now().getDayOfYear();
        membershipDTO.setDaysLeft(daysLeft);

        String firstDay = membershipDTO.getRecording_day().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setFirstDay(firstDay);
        
        String lastDay = membershipDTO.getRecording_day().plusDays(30).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        membershipDTO.setLastDay(lastDay);

    }
}







