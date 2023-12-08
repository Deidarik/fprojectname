package ru.ssau.OOP_lab.exceptions;

public class InterpolationException extends RuntimeException{
    public InterpolationException() {
        super();
    }

    public InterpolationException(String message) {
        super(message);
    }
}
