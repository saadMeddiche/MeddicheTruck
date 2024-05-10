package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionIDto;
import com.MeddicheTruck.mtmain.dtos.VehicleTransactionODto;
import com.MeddicheTruck.mtmain.entities.VehicleTransaction;

public interface VehicleTransactionService extends BaseServiceInterface<VehicleTransaction, VehicleTransactionIDto, VehicleTransactionODto> {
}
