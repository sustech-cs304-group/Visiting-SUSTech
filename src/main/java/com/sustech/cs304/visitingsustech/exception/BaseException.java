package com.sustech.cs304.visitingsustech.exception;

/**
 * Set up base exception format.
 *
 * @author sui_h
 */
public class BaseException extends RuntimeException {
    private final int status;

    public BaseException() {
        super();
        this.status = 500;
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(int status) {
        super();
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
        this.status = 500;
    }

    public int getStatus() {
        return status;
    }
}
