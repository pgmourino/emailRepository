
package com.pgm.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException(Integer id) {
        super(String.format("State with Id %d not found", id));
    }
}
