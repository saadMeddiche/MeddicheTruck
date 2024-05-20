package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.PieceTransactionDto;
import com.MeddicheTruck.mtmain.entities.PieceTransaction;
import com.MeddicheTruck.mtmain.services.PieceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pieceTransactions")
public class PieceTransactionController extends BaseController<PieceTransaction , PieceTransactionDto ,PieceTransactionDto , PieceTransactionService> {

    @Autowired
    PieceTransactionController(PieceTransactionService service) {
        super(service);
    }
}
