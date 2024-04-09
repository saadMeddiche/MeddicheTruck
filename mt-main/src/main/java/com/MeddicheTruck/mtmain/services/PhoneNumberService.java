package com.MeddicheTruck.mtmain.services;

import com.MeddicheTruck.mtcore.base.BaseServiceInterface;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtmain.dtos.PhoneNumberDto;
import com.MeddicheTruck.mtmain.entities.PhoneNumber;
import org.springframework.data.domain.Pageable;

public interface PhoneNumberService extends BaseServiceInterface<PhoneNumber , PhoneNumberDto,PhoneNumberDto> {
    CustomPageResponse<PhoneNumber, PhoneNumberDto> getPhoneNumbersByPersonId(Long personId , String searchTerm , Pageable pageable);
}
