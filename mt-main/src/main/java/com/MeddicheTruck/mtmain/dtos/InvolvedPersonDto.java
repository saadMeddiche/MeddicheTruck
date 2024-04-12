package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtmain.enums.PersonRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class InvolvedPersonDto extends BaseEntityDto {

    @IncludeOnPutRequest
    private Long id;

    @IncludeOnAllRequests
    @NotNull(message = "Transaction Id is required")
    private Long transactionId;

    @IncludeOnAllRequests
    @NotNull(message = "Person Id is required")
    private Long personId;

    @IncludeOnAllRequests
    @NotNull(message = "Person Role is required")
    @EnumValue(enumClass = PersonRole.class , message = "Invalid Person Role, must be either BUYER or SELLER or ...")
    private String personRole;
}
