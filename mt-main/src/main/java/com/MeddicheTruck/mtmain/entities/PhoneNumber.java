package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.MeddicheTruck.mtmain.enums.PhoneNumberType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @Column(unique = true)
    private String number;

    @ManyToOne
    @JsonIgnoreProperties({"phoneNumbers"})
    private Person person;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", type=" + type +
                ", number='" + number +
                '}';
    }

}
