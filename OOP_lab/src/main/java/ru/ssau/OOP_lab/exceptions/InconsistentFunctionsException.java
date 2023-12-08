package ru.ssau.OOP_lab.exceptions;

public class InconsistentFunctionsException extends RuntimeException{
    public InconsistentFunctionsException() {
        super();
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
