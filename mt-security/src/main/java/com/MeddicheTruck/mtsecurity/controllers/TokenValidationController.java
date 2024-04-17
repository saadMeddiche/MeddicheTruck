package com.MeddicheTruck.mtsecurity.controllers;

import com.MeddicheTruck.mtsecurity.dtos.authentication.response.JwtAuthenticationResponse;
import com.MeddicheTruck.mtsecurity.dtos.authentication.response.TokenValidationResponse;
import com.MeddicheTruck.mtsecurity.dtos.tokenValidation.TokenValidationRequest;
import com.MeddicheTruck.mtsecurity.services.JwtService;
import com.MeddicheTruck.mtsecurity.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenValidationController {

    private final JwtService jwtService;

    private  final UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@Valid @RequestBody TokenValidationRequest request) {

        // Load user details by username
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.getUsername());

        // Check if the token is valid
        boolean isValid = jwtService.isTokenValid(request.getToken(), userDetails);

        // Create a response object
        TokenValidationResponse response = new TokenValidationResponse(isValid);

        // Return the response
        return new ResponseEntity<>(response , HttpStatus.OK);

    }
}
