package com.MeddicheTruck.mtsecurity.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @NotNull(message = "The first name of the user can not be null")
    private String firstName;

    @NotNull(message = "The middle name of the user can not be null")
    private String middleName;

    @NotNull(message = "The last name of the user can not be null")
    private String lastName;

    @NotNull(message = "The email of the user can not be null")
    @Email(message = "The email of the user is not valid")
    private String email;

    @NotNull(message = "The birth date of the user can not be null")
    @Past(message = "The birth date of the user can not be in the future")
    private LocalDate birthDate;

}
