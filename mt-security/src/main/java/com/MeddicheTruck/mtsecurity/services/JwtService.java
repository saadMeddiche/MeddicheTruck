package com.MeddicheTruck.mtsecurity.services;

import com.MeddicheTruck.mtsecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(User userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
