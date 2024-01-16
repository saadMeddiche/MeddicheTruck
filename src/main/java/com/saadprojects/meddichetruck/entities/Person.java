package com.saadprojects.meddichetruck.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saadprojects.meddichetruck.embedabbles.FullName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FullName name;

    private LocalDate birthDate;

    private String description;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    private List<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    private List<InvolvedPerson> involvedPersons;
}
