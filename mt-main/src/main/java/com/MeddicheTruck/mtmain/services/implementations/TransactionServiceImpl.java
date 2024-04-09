package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtmain.dtos.TransactionDto;
import com.MeddicheTruck.mtmain.entities.Transaction;
import com.MeddicheTruck.mtmain.repositories.TransactionRepository;
import com.MeddicheTruck.mtmain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TransactionServiceImpl extends BaseService<Transaction, TransactionDto, TransactionDto , TransactionRepository> implements TransactionService {

    @Autowired
    TransactionServiceImpl(TransactionRepository repository) {
        super(repository, Transaction.class, TransactionDto.class, TransactionDto.class);
    }

    @Override
    public String recordName() {
        return "transaction";
    }
}
