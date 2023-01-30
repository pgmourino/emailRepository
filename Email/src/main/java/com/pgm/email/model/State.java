package com.pgm.email.model;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum State {
    
    SENT(1),
    DRAFT(2),
    REMOVED(3),
    SPAM(4);
    
    private int value;

    public static State findByCode(int code) {
    return Arrays
            .stream(values())
            .filter(state -> state.getValue() == code)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
}
    

}
