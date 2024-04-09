package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.TransactionDto;
import com.MeddicheTruck.mtmain.entities.Transaction;
import com.MeddicheTruck.mtmain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController extends BaseController<Transaction, TransactionDto, TransactionDto, TransactionService> {
    @Autowired
    TransactionController(TransactionService service) {
        super(service);
    }
}
