package com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationException extends RuntimeException {
    private final String error;
}