package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtmain.dtos.InvolvedPersonDto;
import com.MeddicheTruck.mtmain.dtos.PersonDto;
import com.MeddicheTruck.mtmain.dtos.TransactionDto;
import com.MeddicheTruck.mtmain.entities.InvolvedPerson;
import com.MeddicheTruck.mtmain.entities.Person;
import com.MeddicheTruck.mtmain.entities.Transaction;
import com.MeddicheTruck.mtmain.enums.PersonRole;
import com.MeddicheTruck.mtmain.repositories.InvolvedPersonRepository;
import com.MeddicheTruck.mtmain.services.InvolvedPersonService;
import com.MeddicheTruck.mtmain.services.PersonService;
import com.MeddicheTruck.mtmain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class InvolvedPersonImpl extends BaseService<InvolvedPerson, InvolvedPersonDto, InvolvedPersonDto, InvolvedPersonRepository> implements InvolvedPersonService {

    TransactionService transactionService;

    PersonService personService;

    @Autowired
    public InvolvedPersonImpl(InvolvedPersonRepository repository , TransactionService transactionService , PersonService personService) {
        super(repository, InvolvedPerson.class, InvolvedPersonDto.class, InvolvedPersonDto.class);
        this.transactionService = transactionService;
        this.personService = personService;
    }

    @Override
    public String recordName() {
        return "involvement";
    }


    @Override
    public void globalValidation(InvolvedPersonDto involvedPersonDto){
        validateObject(involvedPersonDto);

        // Check if the transaction and the person exist
        throwExceptionIf(transactionService::doesNotExistById , involvedPersonDto.getTransactionId() , DoNotExistException::new , String.format("The transaction with id %d does not exist" , involvedPersonDto.getTransactionId()));
        throwExceptionIf(personService::doesNotExistById , involvedPersonDto.getPersonId() , DoNotExistException::new , String.format("The person with id %d does not exist" , involvedPersonDto.getPersonId()));

        // Check if the person is already involved in the transaction
        if(repository.existsByPersonIdAndTransactionIdAndPersonRole(involvedPersonDto.getPersonId() , involvedPersonDto.getTransactionId() , PersonRole.valueOf(involvedPersonDto.getPersonRole()))){
            throw new ValidationException(String.format("The person with id %d is already involved in the transaction with id %d as a %s" , involvedPersonDto.getPersonId() , involvedPersonDto.getTransactionId() , involvedPersonDto.getPersonRole()));
        }
    }

    @Override
    public CustomPageResponse<Transaction, TransactionDto> getTransactionsByPersonId(Long id , Pageable pageable){
        throwExceptionIf(personService::doesNotExistById , id , DoNotExistException::new , String.format("The person with id %d does not exist" , id));

        Page<Transaction> transactions = repository.findTransactionsByPersonId(id, pageable);

        return new CustomPageResponse<>(transactions , TransactionDto.class);
    }

    @Override
    public CustomPageResponse<Person, PersonDto> getPersonsByTransactionId(Long id , Pageable pageable) {
        throwExceptionIf(transactionService::doesNotExistById , id , DoNotExistException::new , String.format("The transaction with id %d does not exist" , id));

        Page<Person> persons = repository.findPersonsByTransactionId(id , pageable);

        return new CustomPageResponse<>(persons , PersonDto.class);
    }
}
