package com.MeddicheTruck.mtsecurity.services.implementations;


import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import com.MeddicheTruck.mtsecurity.services.UserService;
import com.MeddicheTruck.mtsecurity.services.validations.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValidationService validation;

    private final SecurityUserDetailsService securityUserDetailsService;


    public User createUser(User user){

        validation.validateUserOnCreating(user);

        return userRepository.save(user);
    }

    public User userLoggedIn(User user){
        user.setLastLogin(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public void updateProfile(UserUpdateDto userUpdateDto) {

        User authenticatedUser = securityUserDetailsService.getCurrentAuthenticatedUser()
                .orElseThrow(() -> new ValidationException("Authentication needed to update profile"));

        if(userRepository.existsByEmailAndIdNot(userUpdateDto.getEmail(), authenticatedUser.getId()))
            throw new ValidationException("Email already exists");

        authenticatedUser.setFullName(new FullName(
                userUpdateDto.getFirstName(),
                userUpdateDto.getMiddleName(),
                userUpdateDto.getLastName()
        ));

        authenticatedUser.setEmail(userUpdateDto.getEmail());

        authenticatedUser.setBirthDate(userUpdateDto.getBirthDate());

        userRepository.save(authenticatedUser);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
