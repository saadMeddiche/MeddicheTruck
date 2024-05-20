package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.PersonDto;
import com.MeddicheTruck.mtmain.entities.Person;
import com.MeddicheTruck.mtmain.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController extends BaseController<Person, PersonDto, PersonDto , PersonService> {
    @Autowired
    PersonController(PersonService service) {
        super(service);
    }
}
