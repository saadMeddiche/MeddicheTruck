package com.MeddicheTruck.mtmain.controllers;

import com.MeddicheTruck.mtcore.base.BaseController;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionODto;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;
import com.MeddicheTruck.mtmain.services.VehicleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicleTransactions")
public class VehicleTransactionController extends BaseController<VehicleTransaction, VehicleTransactionIDto, VehicleTransactionODto, VehicleTransactionService> {

    @Autowired
    VehicleTransactionController(VehicleTransactionService service) {
        super(service);
    }
}