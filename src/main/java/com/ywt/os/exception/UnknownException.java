package com.ywt.os.exception;

/**
 * @author: YwT
 * @create: 2018-11-25 00:12
 **/
public class UnknownException extends RuntimeException {

    private String message;

    public UnknownException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
