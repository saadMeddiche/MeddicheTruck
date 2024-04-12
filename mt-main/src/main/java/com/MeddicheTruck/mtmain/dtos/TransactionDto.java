package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtmain.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class TransactionDto extends BaseEntityDto {

    @IncludeOnPutRequest
    private Long id;

    @IncludeOnAllRequests
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is cannot be blank")
    private String name;

    @IncludeOnAllRequests
    @NotNull(message = "Description is required")
    private String description;

    @IncludeOnAllRequests
    @NotNull(message = "Transaction Time is required")
    private LocalDateTime timeTransaction;

    @IncludeOnAllRequests
    @EnumValue(enumClass = TransactionType.class , message = "Invalid Transaction Type, must be either BUYING or SELLING")
    @NotNull(message = "Transaction Type is required")
    private String type;
}
