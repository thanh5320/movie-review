package com.hust.movie_review.config.exception;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
        log.error(ex.getMessage());
        String name = ex.getParameterName();
        return generateExceptionResponse(BAD_REQUEST.value(), "Missing " + name);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return generateExceptionResponse(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handlerException(BadCredentialsException ex, WebRequest request) {
        log.error(ex.getMessage());
        return generateExceptionResponse(NotMatchException.CODE, "Incorrect account or password!");
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handlerException(InternalAuthenticationServiceException ex, WebRequest request) {
        log.error(ex.getMessage());
        return generateExceptionResponse(NotMatchException.CODE, "Incorrect account or password!");
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handlerApiException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return generateExceptionResponse(BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler(UndeclaredThrowableException.class)
    public ResponseEntity<Object> handlerThrowableException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        Throwable undeclaredThrowable = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
        if (undeclaredThrowable instanceof ApiException) {
            return handlerApiException((ApiException) undeclaredThrowable, request);
        }
        else if (undeclaredThrowable instanceof InValidException) {
            return handlerInValidException((InValidException) undeclaredThrowable);
        }
        return generateExceptionResponse(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(InValidException.class)
    public ResponseEntity<Object> handlerInValidException(InValidException ex) {
        log.error(ex.getMessage());
        return generateExceptionResponse(ex.CODE, ex.getMessage());
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<Object> handlerMisMatchException(NotMatchException ex) {
        log.error(ex.getMessage());
        return generateExceptionResponse(ex.CODE, ex.getMessage());
    }

    @SneakyThrows
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handlerException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> messageErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            messageErrors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        return generateExceptionResponse(InValidException.CODE, messageErrors.toString());
    }


    private ResponseEntity<Object> generateExceptionResponse(Integer statusCode, String message) {
        HashMap<String, Object> bodyError = new HashMap<>();
        bodyError.put("message", message);
        bodyError.put("code", statusCode);
        return ResponseEntity.status(statusCode).body(bodyError);
    }

    private ResponseEntity<Object> generateExceptionResponse(Integer statusCode, String message, T data) {
        HashMap<String, Object> bodyError = new HashMap<>();
        bodyError.put("message", message);
        bodyError.put("code", statusCode);
        bodyError.put("data", data);
        return ResponseEntity.status(statusCode).body(bodyError);
    }
}
