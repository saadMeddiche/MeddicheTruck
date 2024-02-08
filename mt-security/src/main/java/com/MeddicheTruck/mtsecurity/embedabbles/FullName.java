package com.MeddicheTruck.mtsecurity.embedabbles;

import jakarta.persistence.Embeddable;
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
