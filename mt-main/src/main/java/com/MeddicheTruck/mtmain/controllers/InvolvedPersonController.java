package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.InvolvedPersonDto;
import com.MeddicheTruck.mtmain.entities.InvolvedPerson;
import com.MeddicheTruck.mtmain.services.InvolvedPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/involvements")
public class InvolvedPersonController extends BaseController<InvolvedPerson, InvolvedPersonDto , InvolvedPersonDto , InvolvedPersonService> {

    @Autowired
    InvolvedPersonController(InvolvedPersonService service) {
        super(service);
    }

    @GetMapping("/transactions/{id}/person")
    ResponseEntity<?> getTransactionsByPersonId(@PathVariable Long id,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(service.getTransactionsByPersonId(id , pageable));
    }

    @GetMapping("/persons/{id}/transaction")
    ResponseEntity<?> getPersonsByTransactionId(@PathVariable Long id,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(service.getPersonsByTransactionId(id , pageable));
    }

}
