package com.MeddicheTruck.mtsecurity.services.implementations;


import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword().getHashedPassword(), user.getAuthorities()
        );
    }

    public Optional<User> getCurrentAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        return userRepository.findByUsername(username);
    }

}
