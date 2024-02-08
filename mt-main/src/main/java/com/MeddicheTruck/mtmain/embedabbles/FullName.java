package com.MeddicheTruck.mtmain.embedabbles;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FullName {

    @NotNull(message = "The first name can not be null")
    @NotBlank(message = "The first name can not be blank")
    private String first;

    private String middle;

    @NotNull(message = "The last name can not be null")
    @NotBlank(message = "The last name can not be blank")
    private String last;

}
