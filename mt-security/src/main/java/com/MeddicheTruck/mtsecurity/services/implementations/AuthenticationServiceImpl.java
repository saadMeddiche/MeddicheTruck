package com.MeddicheTruck.mtsecurity.services.implementations;

import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtcore.services.SchemaCreationService;
import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignInRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.request.SignUpRequest;
import com.MeddicheTruck.mtsecurity.dtos.authentication.response.JwtAuthenticationResponse;

import com.MeddicheTruck.mtsecurity.embeddables.Password;
import com.MeddicheTruck.mtsecurity.entities.Authority;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.enums.BaseAuthority;
import com.MeddicheTruck.mtsecurity.services.*;
import com.MeddicheTruck.mtsecurity.services.validations.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final UserValidationService userValide;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final SchemaCreationService schemaCreationService;


    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        // Build a user object from the signup request
        User user = buildUser(request);

        // Create the user in the database
        User createdUser = userService.createUser(user);

        // Create the schema for the user
        schemaCreationService.createTenantForUser(user.getUsername());

        // Generate a JWT token for the registered user
        String jwt = jwtService.generateToken(createdUser);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {

        // Validate if the user exists
        userValide.validateUsernameExistent(request.getUsername());

        // Authenticate the user using the provided credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Retrieve the user details from the database
        User user = userService.getByUsername(request.getUsername())
                .orElseThrow(() -> new ValidationException("User not found"));

        // Update the user in the database
        User updatedUser = userService.userLoggedIn(user);

        // Generate a JWT token for the authenticated user
        String jwt = jwtService.generateToken(updatedUser);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }



    private User buildUser(SignUpRequest request) {

        // Build a User object from the SignUpRequest
        return User.builder()
                .username(request.getUsername())
                .fullName(new FullName(request.getFirstName(), request.getMiddleName(), request.getLastName()))
                .email(request.getEmail())
                .password(new Password(request.getPassword()))  // --- Encrypt the password
                .birthDate(request.getBirthDate())
                .creationDateAccount(LocalDate.now())
                .authorities(List.of(
                        new Authority(BaseAuthority.ACCESS_USER_DASHBOARD)
                ))
                .build();
    }
}
