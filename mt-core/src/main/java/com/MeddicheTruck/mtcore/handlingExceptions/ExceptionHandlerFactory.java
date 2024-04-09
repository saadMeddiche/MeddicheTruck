package com.MeddicheTruck.mtcore.handlingExceptions;


import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.query.QueryArgumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandlerFactory {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleValidationExceptions(AlreadyExistsException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateValidationException.class)
    public ResponseEntity<?> handleDateValidationException(DateValidationException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<?> handleEmptyException(EmptyException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DoNotExistException.class)
    public ResponseEntity<?> handleDoNotExistException(DoNotExistException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryArgumentException.class)
    public ResponseEntity<?> handleQueryArgumentException(QueryArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> handleMissingPathVariableException(MissingPathVariableException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>(List.of("Password or username is incorrect") , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Yeh I know ,  I also do not like this one
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleDHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        // if the format of the date is not correct
        if(exception.getMessage().contains("Failed to deserialize java.time.LocalDate")){
            return new ResponseEntity<>(List.of("Respect the format YYYY-MM-DD") , HttpStatus.BAD_REQUEST);
        }

        // if the request body is empty
        if(exception.getMessage().contains("JSON parse error: No content to map due to end-of-input")){
            return new ResponseEntity<>(List.of("The request body can not be empty") , HttpStatus.BAD_REQUEST);
        }

        if(exception.getMessage().contains("JSON parse error: Cannot deserialize value of type `java.lang.Long` from String")){
            return new ResponseEntity<>(List.of("Tried to insert a string in column with type long") , HttpStatus.BAD_REQUEST);
        }

        if(exception.getMessage().contains("JSON parse error: Unexpected character")){
            return new ResponseEntity<>(List.of("Unexpected character in the request body") , HttpStatus.BAD_REQUEST);
        }

        if(exception.getMessage().contains("not one of the values accepted for Enum class: [BIKE, CAR, TRUCK")){
            return new ResponseEntity<>(List.of("The type of the vehicle is invalid") , HttpStatus.BAD_REQUEST);
        }

        if(exception.getMessage().contains("not one of the values accepted for Enum class: [HYDRAULIC_FUEL, COMBUSTION_DIESEL")){
            return new ResponseEntity<>(List.of("The engine type of the vehicle is invalid") , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(List.of("Development Purpose #1 , This error Not handled Yet") , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(SQLIntegrityConstraintViolationException e) {

        // The errors are not handled yet

        return new ResponseEntity<>(List.of("Development Purpose #2 , This error Not handled Yet") , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException e) {

        if(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("phone_number")){
            return new ResponseEntity<>(List.of("This phone number has been already given to another person") , HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(List.of("Development Purpose #3 , This error Not handled Yet") , HttpStatus.BAD_REQUEST);
    }

    // Thanks To Yassine Sahyane
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleDateMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Return list of errors
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {

        // Return list of errors
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}
