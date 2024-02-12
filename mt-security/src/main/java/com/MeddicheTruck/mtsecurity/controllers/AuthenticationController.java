package com.MeddicheTruck.mtsecurity.controllers;


import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignInRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignUpRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.response.JwtAuthenticationResponse;
import com.MeddicheTruck.mtsecurity.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signUp")
    @PreAuthorize("permitAll()")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    @PreAuthorize("permitAll()")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest request) {
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
