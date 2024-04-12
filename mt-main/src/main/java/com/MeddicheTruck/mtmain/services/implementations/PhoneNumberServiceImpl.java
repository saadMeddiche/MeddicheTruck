package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtmain.dtos.PhoneNumberDto;
import com.MeddicheTruck.mtmain.entities.PhoneNumber;
import com.MeddicheTruck.mtmain.repositories.PhoneNumberRepository;
import com.MeddicheTruck.mtmain.services.PersonService;
import com.MeddicheTruck.mtmain.services.PhoneNumberService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@Primary
public class PhoneNumberServiceImpl extends BaseService<PhoneNumber, PhoneNumberDto, PhoneNumberDto , PhoneNumberRepository> implements PhoneNumberService {

    PersonService personService;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository,
                                  PersonService personService) {
        super(phoneNumberRepository , PhoneNumber.class , PhoneNumberDto.class , PhoneNumberDto.class);
        this.personService = personService;
    }

    @Override
    public String recordName() {
        return "phone number";
    }

    @Override
    public void globalValidation(PhoneNumberDto phoneNumberDto) {

        throwExceptionIf(personService::doesNotExistById , phoneNumberDto.getPersonId() , DoNotExistException::new , String.format("The person with id %d does not exist" , phoneNumberDto.getPersonId()));

        validateObject(phoneNumberDto);
    }

    @Override
    public void saveValidation(PhoneNumberDto phoneNumberDto) {
        throwExceptionIf(repository::existsPhoneNumberByNumber , phoneNumberDto.getNumber() , DoNotExistException::new , String.format("The phone number %s already exists" , phoneNumberDto.getNumber()));
        globalValidation(phoneNumberDto);
    }

    @Override
    public void updateValidation(PhoneNumberDto phoneNumberDto) {
        // if the phone number already exists, and it is not the same phone number that we are updating
        if(repository.existsPhoneNumberByNumberAndIdNot(phoneNumberDto.getNumber() , phoneNumberDto.getId()))
            throw new DoNotExistException(String.format("The phone number %s already exists" , phoneNumberDto.getNumber()));

        globalValidation(phoneNumberDto);
    }

    @Override
    public CustomPageResponse<PhoneNumber, PhoneNumberDto> getPhoneNumbersByPersonId(Long personId, String searchTerm, Pageable pageable) {

        throwExceptionIf(personService::doesNotExistById , personId , DoNotExistException::new , String.format("The person with id %d does not exist" , personId));

        Page<PhoneNumber> phoneNumbersPage = repository.findPhoneNumbersByPersonId(personId , searchTerm , pageable);

        return new CustomPageResponse<>(phoneNumbersPage , PhoneNumberDto.class);
    }

}
