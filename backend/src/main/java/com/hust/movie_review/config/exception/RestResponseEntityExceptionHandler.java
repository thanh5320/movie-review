package com.hust.movie_review.config.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler<T> {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(MissingServletRequestParameterException ex, WebRequest request) {
        String name = ex.getParameterName();
        return generateExceptionResponse(BAD_REQUEST.value(), "Missing " + name);
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Object> handlerException(java.lang.Exception ex, WebRequest request) {
        return generateExceptionResponse(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handlerApiException(java.lang.Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return generateExceptionResponse(BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler(UndeclaredThrowableException.class)
    public ResponseEntity<Object> handlerThrowableException(java.lang.Exception ex, WebRequest request) {
        Throwable undeclaredThrowable = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
        if (undeclaredThrowable instanceof ApiException) {
            return handlerApiException((ApiException) undeclaredThrowable, request);
        }
        return generateExceptionResponse(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase());
    }



//    @ExceptionHandler(SsoException.class)
//    public ResponseEntity<Object> handlerEzException( SsoException ex, WebRequest request) {
//        return generateExceptionResponse(ex.getCode(), ex.getMessage());
//    }

    @ExceptionHandler(InValidException.class)
    public ResponseEntity<Object> handlerIsVaildException(InValidException ex) {
        return generateExceptionResponse(ex.CODE, ex.getMessage());
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<Object> handlerMisMatchException(NotMatchException ex) {
            return generateExceptionResponse(ex.CODE, ex.getMessage());
    }

    @SneakyThrows
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handlerException( MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> messageErrors = new ArrayList<>();
        for (FieldError fieldError: fieldErrors) {
            messageErrors.add(fieldError.getField()+": "+fieldError.getDefaultMessage());
        }
        throw new InValidException(messageErrors.toString());
    }


    private ResponseEntity<Object> generateExceptionResponse(Integer statusCode, String message) {
        if (statusCode == INTERNAL_SERVER_ERROR.value()) {
            log.error(message);
        }
        HashMap<String, Object> bodyError = new HashMap<>();
        bodyError.put("message", message);
        bodyError.put("code", statusCode);
        log.error("", message ,bodyError.toString());
        return ResponseEntity.status(statusCode).body(bodyError);
    }

    private ResponseEntity<Object> generateExceptionResponse(Integer statusCode, String message, T data) {
        if (statusCode == INTERNAL_SERVER_ERROR.value()) {
            log.error(message);
        }

        HashMap<String, Object> bodyError = new HashMap<>();
        bodyError.put("message", message);
        bodyError.put("code", statusCode);
        bodyError.put("data", data);

        log.error("", message ,bodyError.toString());
        return ResponseEntity.status(statusCode).body(bodyError);
    }
}
