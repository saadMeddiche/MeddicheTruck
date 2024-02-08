package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/*
* This class is used to represent the involved persons in a transaction and its role in that transaction
* */


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvolvedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"involvedPersons" , "pieces"})
    private Transaction transaction;

    @ManyToOne
    @JsonIgnoreProperties("involvedPersons")
    private Person person;

    @ManyToOne
    private PersonRoleInTransaction personRoleInTransaction;

}
