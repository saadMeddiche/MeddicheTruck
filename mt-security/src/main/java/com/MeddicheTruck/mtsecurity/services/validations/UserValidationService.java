package com.MeddicheTruck.mtsecurity.services.validations;



import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import com.MeddicheTruck.mtcore.services.validations.BaseValidation;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class UserValidationService extends BaseValidation {

    private UserRepository userRepository;

    public UserValidationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void validateUserOnCreating(User user) {

        validateObject(user);

        throwExceptionIf(EMAIL_ALREADY_EXISTS, user, AlreadyExistsException::new, "Email Already Exists");

        throwExceptionIf(USERNAME_ALREADY_EXISTS, user, AlreadyExistsException::new, "Username Already Exists");

    }

    public void validateUsernameExistent(String username) {
        User user = User.builder().username(username).build();
        throwExceptionIf(USERNAME_DO_NOT_EXISTS, user, DoNotExistException::new, "Username Does Not Exist");
    }

    private final Predicate<User> EMAIL_ALREADY_EXISTS = user -> userRepository.existsByEmail(user.getEmail());

    private final Predicate<User> USERNAME_ALREADY_EXISTS = user -> userRepository.existsByUsername(user.getUsername());

    private final Predicate<User> USERNAME_DO_NOT_EXISTS = user -> !userRepository.existsByUsername(user.getUsername());


}
