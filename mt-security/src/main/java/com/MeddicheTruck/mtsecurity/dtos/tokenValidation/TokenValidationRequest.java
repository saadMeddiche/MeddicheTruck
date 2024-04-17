package com.MeddicheTruck.mtsecurity.dtos.tokenValidation;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationRequest {

    @NotNull(message = "The username of role can not be null")
    private String username;

    @NotNull(message = "The token can not be null")
    private String token;
}
