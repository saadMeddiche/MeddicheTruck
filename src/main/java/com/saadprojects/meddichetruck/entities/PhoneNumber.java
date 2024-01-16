package com.saadprojects.meddichetruck.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saadprojects.meddichetruck.enums.PhoneNumberType;
import jakarta.persistence.*;
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
    @JsonIgnoreProperties({"phoneNumbers" , "involvedPersons"})
    private Person person;

}
