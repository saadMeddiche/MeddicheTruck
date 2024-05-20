package com.MeddicheTruck.mtsecurity.controllers;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.services.UserService;
import com.MeddicheTruck.mtsecurity.services.implementations.SecurityUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class MyProfileController {

    private final SecurityUserDetailsService securityUserDetailsService;

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getProfile() {

        User user = securityUserDetailsService.getCurrentAuthenticatedUser()
                .orElseThrow(() -> new ValidationException("User not authenticated"));

        UserUpdateDto userUpdateDto = new ModelMapper().map(user, UserUpdateDto.class);

        return ResponseEntity.ok(userUpdateDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {

        userService.updateProfile(userUpdateDto);

        return ResponseEntity.ok().build();
    }
}
