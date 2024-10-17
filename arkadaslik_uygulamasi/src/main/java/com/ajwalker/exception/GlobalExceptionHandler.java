package com.ajwalker.exception;

import com.ajwalker.dto.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*
    * Tanımlaması yapılmayan diğer tüm hataları yakalamak için RuntimeException kullanılır.
    */

    public ResponseEntity<ErrorMessage> runtimeException(RuntimeException e){
        return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(ArkadaslikException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> arkadaslikExceptionHandler(ArkadaslikException e) {

        //ResponseEntity.ok -> 200 herşey yolunda
        //ResponseEntity.badRequest -> 400 gelen istek hatalı
        //ResponseEntity.internalServerError -> 500 sunucu tarafında bir hata oldu

       return createResponseEntity(e.getErrorType(), e.getErrorType().httpStatus, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> methodArgNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> fieldErrors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            //fieldError.getField() -> hata fırlatan nesnenin değişlen adı
            fieldErrors.add("Değişken Adı: "+ fieldError.getField() + " - Hata Detayı - " + fieldError.getDefaultMessage());
        });
        return createResponseEntity(ErrorType.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, fieldErrors);
    }

    public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, HttpStatus httpStatus, List<String> fields) {
        log.error("TÜM HATALARIN GEÇTİĞİ NOKTA... " + errorType+ " "+ fields);
        return new ResponseEntity<>(ErrorMessage.builder()
                .fields(fields)
                .success(false)
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build(), httpStatus);
    }
}
