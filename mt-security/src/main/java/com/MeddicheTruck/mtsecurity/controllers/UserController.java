package com.MeddicheTruck.mtsecurity.controllers;

import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.services.AuthenticationService;
import com.MeddicheTruck.mtsecurity.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {

        userService.updateProfile(userUpdateDto);

        return ResponseEntity.ok().build();
    }
}
