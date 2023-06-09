package com.sustech.cs304.visitingsustech.exception;

/**
 * Set up update user info exception format.
 *
 * @author sui_h
 */
public class UpdateUserInfoException extends BaseException{
    public UpdateUserInfoException() {
        super("Bad Request", 400);
    }

    public UpdateUserInfoException(String message) {
        super(message, 400);
    }

    public UpdateUserInfoException(int status) {
        super("Bad Request", status);
    }

    public UpdateUserInfoException(String message, int status) {
        super(message, status);
    }
}
