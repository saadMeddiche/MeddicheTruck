package com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private final String error;
}
