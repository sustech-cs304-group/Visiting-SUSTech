package com.sustech.cs304.visitingsustech.exception;

/**
 * Set up application exception format.
 *
 * @author sui_h
 */
public class AppointmentException extends BaseException{
    public AppointmentException() {
        super("Bad Request", 400);
    }

    public AppointmentException(String message) {
        super(message, 400);
    }

    public AppointmentException(int status) {
        super("Bad Request", status);
    }

    public AppointmentException(String message, int status) {
        super(message, status);
    }
}
