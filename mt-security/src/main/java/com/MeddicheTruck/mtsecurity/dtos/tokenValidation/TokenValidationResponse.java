package com.MeddicheTruck.mtsecurity.dtos.tokenValidation;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationResponse {
    private Boolean isValid;
}
