package com.ajwalker.exception;

import lombok.Getter;

@Getter
public class OkulOtomasyonException extends RuntimeException {
    private ErrorType errorType;
    public OkulOtomasyonException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
