package com.MeddicheTruck.mtmain.clones;

import com.MeddicheTruck.mtcore.annotations.EnumValue;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
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
public class PieceTransactionUpdateClone {

    @NotNull(message = "The id of the transaction can not be null")
    public Long id;

    @NotNull(message = "The date of the transaction can not be null")
    private LocalDate date;

    @NotNull(message = "The time of the transaction can not be null")
    private LocalTime time;

    private String description;

    @EnumValue(enumClass = TransactionType.class, message = "The transaction type must be either BUY or SELL")
    private String type;

    @IncludeOnAllRequests
    @NotNull(message = "The price of the transaction can not be null")
    @Positive(message = "The quantity of the transaction must be positive")
    private Double price;
}
