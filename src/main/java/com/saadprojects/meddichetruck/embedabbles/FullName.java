package com.saadprojects.meddichetruck.embedabbles;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FullName {

    private String first;

    private String middle;

    private String last;
}
