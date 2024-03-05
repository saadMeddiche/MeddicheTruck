package com.MeddicheTruck.mtsecurity.services;


import com.MeddicheTruck.mtsecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User getByUsername(String username);

    User getByEmail(String email);

    User createUser(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
