package com.gym.Membership.controllers;


import com.gym.Membership.dto.PersonDTO;
import com.gym.Membership.models.Person;
import com.gym.Membership.service.PeopleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService ,ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.peopleService = peopleService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid PersonDTO personDTO,
                                          BindingResult bindingResult) {

        Person personToAdd = convertToPerson(personDTO);

        if (bindingResult.hasErrors()) {

        }

        peopleService.register(personToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<Person> getAll() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable("id") int id) {
        return peopleService.findById(id);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

}







