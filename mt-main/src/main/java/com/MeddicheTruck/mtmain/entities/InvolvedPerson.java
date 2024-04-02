package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
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
public class InvolvedPerson extends BaseEntity {

    @ManyToOne
    @JsonIgnoreProperties({"involvedPersons" , "pieces"})
    private Transaction transaction;

    @ManyToOne
    @JsonIgnoreProperties("involvedPersons")
    private Person person;

    @ManyToOne
    private PersonRole personRole;

    @Override
    public String toString() {
        return "InvolvedPerson{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", person=" + person +
                ", personRole=" + personRole +
                '}';
    }

}
