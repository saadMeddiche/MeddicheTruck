package com.MeddicheTruck.mtcore.services.validations;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

import java.util.Set;
import java.util.function.Predicate;

public abstract class BaseValidation {

    protected  <O> void validateObject(O object) {

        Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(object)
                .stream()
                .findFirst()
                .ifPresent(violation -> {
                    throw new ConstraintViolationException(Set.of(violation));
                });

    }

    protected  <O, E extends Exception> void throwExceptionIf(Predicate<O> predicate, O value, ExceptionSupplier<E> exceptionSupplier, String message) throws E {
        if (predicate.test(value)) throw exceptionSupplier.get(message);
    }

    @FunctionalInterface
    protected interface ExceptionSupplier<E extends Exception> {
        E get(String message);
    }
}
