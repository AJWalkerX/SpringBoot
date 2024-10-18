package com.ajwalker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500, "Sunucuda beklenmiyen bir hata! ", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(6001, "Girilen şifreler uyuşmamaktadır!", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD_ERROR(5001, "Kullanıcı adı veya şifre hatalıdır!", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN_ERROR(9001, "Geçersiz token bilgisi!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND_ERROR(6003, "Kullanıcı Bulunamadı!", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;


}
