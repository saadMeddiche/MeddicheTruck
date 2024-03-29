package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtmain.listeners.PersonListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.TenantId;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(PersonListener.class)
public class Person {

    @TenantId
    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Valid
    @NotNull(message = "The name of the person can not be null")
    private FullName name;

    private LocalDate birthDate;

    private String description;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    @RestResource(exported=false)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    private List<InvolvedPerson> involvements = new ArrayList<>();

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name.getFirst() + "|" + name.getMiddle() + "|" + name.getLast() +
                ", birthDate=" + birthDate +
                ", description='" + description +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
