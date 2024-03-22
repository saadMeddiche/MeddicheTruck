package com.MeddicheTruck.mtsecurity.controllers;

import com.MeddicheTruck.mtsecurity.dtos.authentication.response.JwtAuthenticationResponse;
import com.MeddicheTruck.mtsecurity.dtos.authentication.response.TokenValidationResponse;
import com.MeddicheTruck.mtsecurity.services.JwtService;
import com.MeddicheTruck.mtsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenValidationController {

    private final JwtService jwtService;

    private  final UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken() {
        TokenValidationResponse response = new TokenValidationResponse(true);
        return new ResponseEntity<>(response , HttpStatus.OK);
//       return ResponseEntity.ok(jwtService.isTokenValid(jwtAuthenticationResponse.getToken(), userService.userDetailsService().loadUserByUsername(jwtService.extractUserName(jwtAuthenticationResponse.getToken()))));
    }
}
