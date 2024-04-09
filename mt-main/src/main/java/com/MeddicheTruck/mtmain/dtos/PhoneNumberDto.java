package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtmain.enums.PhoneNumberType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class PhoneNumberDto extends BaseEntityDto{

    @IncludeOnPutRequest
    private Long id;

    @IncludeOnAllRequests
    @NotNull(message = "The type of the phone number can not be null")
    @EnumValue(enumClass = PhoneNumberType.class, message = "The type of the phone number can only be PERSONAL, WORK, FIX or ENTERPRISE")
    private String type;

    @IncludeOnAllRequests
    @NotNull(message = "The number of the phone number can not be null")
    private String number;

    @IncludeOnAllRequests
    @NotNull(message = "The person id of the phone number can not be null")
    private Long personId;

}
