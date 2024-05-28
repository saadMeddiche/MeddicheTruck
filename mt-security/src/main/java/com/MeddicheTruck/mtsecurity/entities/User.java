package com.MeddicheTruck.mtsecurity.entities;


import com.MeddicheTruck.mtcore.embedabbles.FullName;

import com.MeddicheTruck.mtsecurity.embeddables.Password;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users", schema = "security_schema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FullName fullName;

    private String username;

    private String email;

    private Password password;

    private LocalDate birthDate;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate creationDateAccount;

    private LocalDate lastLogin;

    @ManyToMany(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            schema = "security_schema",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .toList();
    }
}
