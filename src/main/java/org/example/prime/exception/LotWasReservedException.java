package org.example.prime.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LotWasReservedException extends Exception {

    public LotWasReservedException(String message) {
        super(message);
    }
}
