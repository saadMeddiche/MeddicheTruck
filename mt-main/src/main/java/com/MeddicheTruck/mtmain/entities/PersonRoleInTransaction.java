package com.MeddicheTruck.mtmain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.TenantId;

/*
* Each Transaction have involved persons in it , and each person has his own role in that transaction . For Example, he can be a buyer or a seller or a manager.
* So this class is used to represent that
* */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRoleInTransaction {

    @TenantId
    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
