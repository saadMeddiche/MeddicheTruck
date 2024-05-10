package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.dtos.PieceTransactionDto;
import com.MeddicheTruck.mtmain.entities.PieceTransaction;
import com.MeddicheTruck.mtmain.repositories.PieceTransactionRepository;
import com.MeddicheTruck.mtmain.services.PersonService;
import com.MeddicheTruck.mtmain.services.PieceService;
import com.MeddicheTruck.mtmain.services.PieceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PieceTransactionServiceImpl extends BaseService<PieceTransaction , PieceTransactionDto , PieceTransactionDto , PieceTransactionRepository> implements PieceTransactionService {

    private final PieceService pieceService;

    private final PersonService personService;
    @Autowired
    public PieceTransactionServiceImpl(PieceTransactionRepository repository, PieceService pieceService, PersonService personService) {
        super(repository , PieceTransaction.class , PieceTransactionDto.class , PieceTransactionDto.class);
        this.pieceService = pieceService;
        this.personService = personService;
    }

    @Override
    public String recordName() {
        return "pieceTransaction";
    }


    @Override
    public void globalValidation(PieceTransactionDto pieceTransactionDto) {
        throwExceptionIf(pieceService::doesNotExistById , pieceTransactionDto.getPieceId() , DoNotExistException::new , String.format("The piece with id %d does not exist" , pieceTransactionDto.getPieceId()));
        throwExceptionIf(personService::doesNotExistById , pieceTransactionDto.getPersonId() , DoNotExistException::new , String.format("The person with id %d does not exist" , pieceTransactionDto.getPersonId()));
        validateObject(pieceTransactionDto);
    }
}
