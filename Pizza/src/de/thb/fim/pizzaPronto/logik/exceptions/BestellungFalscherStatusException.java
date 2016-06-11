package de.thb.fim.pizzaPronto.logik.exceptions;

public class BestellungFalscherStatusException extends Exception {
    public BestellungFalscherStatusException() {
        super();
    }

    public BestellungFalscherStatusException(String message) {
        super(message);
    }
}
