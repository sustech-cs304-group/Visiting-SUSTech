package com.sustech.cs304.visitingsustech.exception;

/**
 * Set up token exception format.
 *
 * @author sui_h
 */
public class TokenException extends BaseException {
    public TokenException() {
        super("Unauthorized", 401);
    }

    public TokenException(String message) {
        super(message, 401);
    }

    public TokenException(int status) {
        super("Unauthorized", status);
    }

    public TokenException(String message, int status) {
        super(message, status);
    }
}