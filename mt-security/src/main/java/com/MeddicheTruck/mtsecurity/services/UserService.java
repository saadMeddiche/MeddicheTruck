package com.MeddicheTruck.mtsecurity.services;


import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.entities.Role;
import com.MeddicheTruck.mtsecurity.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String username);

    User getByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    void updateProfile(UserUpdateDto user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
