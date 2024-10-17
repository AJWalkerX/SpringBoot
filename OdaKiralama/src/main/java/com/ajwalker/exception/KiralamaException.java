package com.ajwalker.exception;

import lombok.Getter;

@Getter
public class KiralamaException extends RuntimeException {
    private ErrorType errorType;
    public KiralamaException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
