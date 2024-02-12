package com.MeddicheTruck.mtsecurity.dtos.authentication.response;


import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtsecurity.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;

    private FullName name;

    @JsonIgnoreProperties({"users", "permissions"})
    private List<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;

}