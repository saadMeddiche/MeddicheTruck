package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class PersonDto extends BaseEntityDto {
    @IncludeOnPutRequest
    private Long id;

    @IncludeOnAllRequests
    @NotNull(message = "The first name of the person can not be null")
    @NotBlank(message = "The first name of the person can not be blank")
    private String firstName;

    @IncludeOnAllRequests
    @NotNull(message = "The middle name of the person can not be null")
    private String middleName;

    @IncludeOnAllRequests
    @NotNull(message = "The last name of the person can not be null")
    @NotBlank(message = "The last name of the person can not be blank")
    private String lastName;

    @IncludeOnAllRequests
    @NotNull(message = "The birth date of the person can not be null")
    @Past(message = "The birth date of the person can not be in the future")
    private LocalDate birthDate;

    @IncludeOnAllRequests
    @NotNull(message = "The description of the person can not be null")
    private String description;
}
