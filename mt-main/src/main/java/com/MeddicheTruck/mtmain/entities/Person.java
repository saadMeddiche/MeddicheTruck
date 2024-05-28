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

    @Formula("CASE WHEN middle_name IS NULL OR middle_name = '' THEN CONCAT(first_name, ' ', last_name) ELSE CONCAT(first_name, ' ', middle_name, ' ', last_name) END")
    private String fullName;

    private LocalDate birthDate;

    private String mainPhoneNumber;

    private String secondaryPhoneNumber;

    private String description;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<PieceTransaction> pieceTransactions;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<VehicleTransaction> vehicleTransactions;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + firstName + "|" + middleName + "|" + lastName +
                ", birthDate=" + birthDate +
                ", description='" + description +
                '}';
    }
}
