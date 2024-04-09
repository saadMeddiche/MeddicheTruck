package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtmain.dtos.TransactionDto;
import com.MeddicheTruck.mtmain.entities.Transaction;

public interface TransactionService extends BaseServiceInterface<Transaction , TransactionDto ,TransactionDto> {}
