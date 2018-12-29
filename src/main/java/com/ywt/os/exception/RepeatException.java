package com.ywt.os.exception;

/**
 * @author: YwT
 * @create: 2018-12-29 13:47
 **/
public class RepeatException extends RuntimeException {

    private String message;

    public RepeatException(String message) {
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
