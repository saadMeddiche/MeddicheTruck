package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtmain.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Transaction extends BaseEntity {

    private LocalDate date;

    private LocalTime time;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private Person person;

    private Double price;
}
