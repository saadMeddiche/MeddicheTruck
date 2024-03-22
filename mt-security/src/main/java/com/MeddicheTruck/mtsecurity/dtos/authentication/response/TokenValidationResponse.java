package com.MeddicheTruck.mtsecurity.dtos.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenValidationResponse {
    private boolean valid;
}
