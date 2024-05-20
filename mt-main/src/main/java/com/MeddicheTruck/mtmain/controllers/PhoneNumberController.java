package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.PhoneNumberDto;
import com.MeddicheTruck.mtmain.entities.PhoneNumber;
import com.MeddicheTruck.mtmain.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/phoneNumbers")
public class PhoneNumberController extends BaseController<PhoneNumber , PhoneNumberDto , PhoneNumberDto , PhoneNumberService> {

    @Autowired
    PhoneNumberController(PhoneNumberService phoneNumberService) {
        super(phoneNumberService);
    }

    @GetMapping("/person/{personId}")
    ResponseEntity<?> getPhoneNumbersByPersonId(@PathVariable Long personId ,
                                                       @RequestParam(defaultValue = "") String searchTerm ,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getPhoneNumbersByPersonId(personId , searchTerm, pageable));
    }

}
