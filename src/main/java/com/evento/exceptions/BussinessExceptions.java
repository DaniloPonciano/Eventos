package com.evento.exceptions;

public class BussinessExceptions extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final int errorCode = 400;

    public BussinessExceptions(String message) {
        super(message);
    }

    public int getErrorCode(){
        return errorCode;
    }
}
