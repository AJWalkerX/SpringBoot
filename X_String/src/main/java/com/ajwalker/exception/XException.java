package com.ajwalker.exception;

import lombok.Getter;

@Getter
public class XException extends RuntimeException {
    private ErrorType errorType;
    public XException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
