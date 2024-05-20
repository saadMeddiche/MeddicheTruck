package com.MeddicheTruck.mtcore.services.validations;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.function.Predicate;

public class BaseValidation {

    public <O ,AO> void validateObjectAgainstAnotherObject(O object, Class<AO> anotherObject) {
        ModelMapper modelMapper = new ModelMapper();

        AO objectToValidate = modelMapper.map(object, anotherObject);

        validateObject(objectToValidate);
    }

    public  <O> void validateObject(O object) {

        Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(object)
                .stream()
                .findFirst()
                .ifPresent(violation -> {
                    throw new ConstraintViolationException(Set.of(violation));
                });

    }


    public  <O, E extends Exception> void throwExceptionIf(Predicate<O> predicate, O value, ExceptionSupplier<E> exceptionSupplier, String message) throws E {
        if (predicate.test(value)) throw exceptionSupplier.get(message);
    }

    @FunctionalInterface
    public interface ExceptionSupplier<E extends Exception> {
        E get(String message);
    }
}
