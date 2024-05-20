package com.MeddicheTruck.mtsecurity.services;


import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.entities.User;
import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String username);

    User getByEmail(String email);

    User createUser(User user);

    User userLoggedIn(User user);

    void updateProfile(UserUpdateDto user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
