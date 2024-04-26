package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtmain.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class VehicleTransactionDto extends BaseEntityDto {

    @IncludeOnPutRequest
    public Long id;

    @IncludeOnAllRequests
    @NotNull(message = "The date of the transaction can not be null")
    private LocalDate date;

    @IncludeOnAllRequests
    @NotNull(message = "The time of the transaction can not be null")
    private LocalTime time;

    @IncludeOnAllRequests
    private String description;

    @IncludeOnAllRequests
    @EnumValue(enumClass = TransactionType.class, message = "The transaction type must be either BUY or SELL")
    private String type;

    @IncludeOnAllRequests
    @NotNull(message = "The vehicle id of the transaction can not be null")
    private Long vehicleId;

    @IncludeOnAllRequests
    @NotNull(message = "The person id of the transaction can not be null")
    private Long personId;

    @IncludeOnAllRequests
    @NotNull(message = "The price of the transaction can not be null")
    @Positive(message = "The price of the transaction must be positive")
    private Double price;
}