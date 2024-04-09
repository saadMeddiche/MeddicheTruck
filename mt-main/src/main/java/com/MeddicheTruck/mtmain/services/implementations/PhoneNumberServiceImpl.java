package com.MeddicheTruck.mtmain.services.implementations;

import com.MeddicheTruck.mtcore.base.BaseService;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.PhoneNumberDto;
import com.MeddicheTruck.mtmain.entities.PhoneNumber;
import com.MeddicheTruck.mtmain.repositories.PhoneNumberRepository;
import com.MeddicheTruck.mtmain.services.PhoneNumberService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PhoneNumberServiceImpl extends BaseService<PhoneNumber, PhoneNumberDto, PhoneNumberDto , PhoneNumberRepository> implements PhoneNumberService {

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        super(phoneNumberRepository , PhoneNumber.class , PhoneNumberDto.class , PhoneNumberDto.class);
    }

    @Override
    public String recordName() {
        return "phone number";
    }

    @Override
    public CustomPageResponse<PhoneNumber, PhoneNumberDto> getPhoneNumbersByPersonId(Long personId, String searchTerm, Pageable pageable) {

        Page<PhoneNumber> phoneNumbersPage = repository.findPhoneNumbersByPersonId(personId , searchTerm , pageable);

        return new CustomPageResponse<>(phoneNumbersPage , PhoneNumberDto.class);
    }
}
