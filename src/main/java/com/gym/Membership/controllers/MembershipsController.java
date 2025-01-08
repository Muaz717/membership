package com.gym.Membership.controllers;

import com.gym.Membership.repositories.MembershipsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberships")
public class MembershipsController {

    private final ModelMapper modelMapper;
    private final MembershipsRepository membershipsRepository;

    @Autowired
    public MembershipsController(ModelMapper modelMapper, MembershipsRepository membershipsRepository) {
        this.modelMapper = modelMapper;
        this.membershipsRepository = membershipsRepository;
    }


}
