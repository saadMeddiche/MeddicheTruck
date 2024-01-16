package com.saadprojects.meddichetruck.services.implementations;

import com.saadprojects.meddichetruck.entities.User;
import com.saadprojects.meddichetruck.repositories.UserRepository;
import com.saadprojects.meddichetruck.services.UserService;

import com.saadprojects.meddichetruck.services.validations.UserValdiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValdiationService validation;


    public User createUser(User user){

        validation.validateUserOnCreating(user);

        return userRepository.save(user);
    }
    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return getByUsername(username);
            }
        };
    }




}
