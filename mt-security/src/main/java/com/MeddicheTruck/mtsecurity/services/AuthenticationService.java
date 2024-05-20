package com.MeddicheTruck.mtsecurity.services;


import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignInRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignUpRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.response.JwtAuthenticationResponse;
import com.MeddicheTruck.mtsecurity.entities.User;

import java.util.Optional;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);

}
