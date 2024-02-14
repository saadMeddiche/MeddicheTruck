package com.MeddicheTruck.mtsecurity.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Password {

    private String hashedPassword;

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Password(String nonHashedPassword) {
        this.hashedPassword = hashPassword(nonHashedPassword);
    }

     public String hashPassword(String nonHashedPassword) {
         return passwordEncoder.encode(nonHashedPassword);
     }
}