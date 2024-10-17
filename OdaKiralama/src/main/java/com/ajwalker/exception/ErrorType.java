package com.ajwalker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500, "Sunucuda beklenmiyen bir hata! ", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST),
    ODA_DOLU_ERROR(1000, "Oda dolu", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;


}
