package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity {

    private String firstName;

    private String middleName;

    private String lastName;

    @Formula("CONCAT(first_name, ' ', middle_name, ' ', last_name)")
    private String fullName;

    private LocalDate birthDate;

    private String description;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<PieceTransaction> pieceTransactionsAsBuyer;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<VehicleTransaction> vehicleTransactionsAsSeller;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + firstName + "|" + middleName + "|" + lastName +
                ", birthDate=" + birthDate +
                ", description='" + description +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
