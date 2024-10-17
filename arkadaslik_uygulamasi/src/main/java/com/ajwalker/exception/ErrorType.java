package com.ajwalker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500, "Sunucuda beklenmiyen bir hata! ", HttpStatus.INTERNAL_SERVER_ERROR),
    FOLLOW_USERID_FOLLOWINGID_NOTFOUND(5001, "userId yada followingId hatalı", HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;


}
