package de.thb.fim.pizzaPronto.logik.exceptions;

public class KeineBestellungException extends Exception {
    public KeineBestellungException() {
        super();
    }

    public KeineBestellungException(String message) {
        super(message);
    }
}
