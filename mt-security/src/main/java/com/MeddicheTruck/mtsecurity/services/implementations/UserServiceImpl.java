package com.MeddicheTruck.mtsecurity.services.implementations;


import com.MeddicheTruck.mtsecurity.dtos.UserUpdateDto;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import com.MeddicheTruck.mtsecurity.services.JwtService;
import com.MeddicheTruck.mtsecurity.services.UserService;
import com.MeddicheTruck.mtsecurity.services.validations.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValidationService validation;

    private final JwtService jwtService;


    public User createUser(User user){

        validation.validateUserOnCreating(user);

        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void updateProfile(UserUpdateDto userUpdateDto){

    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
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
