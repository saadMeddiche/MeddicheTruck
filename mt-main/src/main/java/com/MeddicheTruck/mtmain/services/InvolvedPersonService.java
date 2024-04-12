package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.InvolvedPersonDto;
import com.MeddicheTruck.mtmain.dtos.PersonDto;
import com.MeddicheTruck.mtmain.dtos.TransactionDto;
import com.MeddicheTruck.mtmain.entities.InvolvedPerson;
import com.MeddicheTruck.mtmain.entities.Person;
import com.MeddicheTruck.mtmain.entities.Transaction;
import org.springframework.data.domain.Pageable;

public interface InvolvedPersonService extends BaseServiceInterface<InvolvedPerson, InvolvedPersonDto, InvolvedPersonDto> {

    CustomPageResponse<Transaction , TransactionDto> getTransactionsByPersonId(Long id , Pageable pageable);

    CustomPageResponse<Person , PersonDto> getPersonsByTransactionId(Long id , Pageable pageable);
}
