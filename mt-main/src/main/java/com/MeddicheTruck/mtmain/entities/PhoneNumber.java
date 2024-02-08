package com.MeddicheTruck.mtmain.entities;

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
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PhoneNumberType type;

    private String number;

    @ManyToOne
    @NotNull(message = "The person of the phone number can not be null")
    @JsonIgnoreProperties({"phoneNumbers" , "involvedPersons"})
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
