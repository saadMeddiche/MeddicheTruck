package com.saadprojects.meddichetruck.services;


import com.saadprojects.meddichetruck.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User getByUsername(String username);

    User getByEmail(String email);

    User createUser(User user);
}
