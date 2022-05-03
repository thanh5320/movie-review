package com.hust.movie_review.common.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hust.movie_review.common.constant.Constant;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DfResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public DfResponse() {
        code = 200;
        message = "OK";
    }

    public DfResponse(String message) {
        this.code = 200;
        this.message = message;
    }

    public DfResponse(String message, T data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public DfResponse(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    public DfResponse(String message, Integer code, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> DfResponse<T> okEntity(T data) {
        return new DfResponse<T>()
                .setCode(Constant.SUCCESS_CODE)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> DfResponse<T> okEntity() {
        return new DfResponse<T>()
                .setCode(Constant.SUCCESS_CODE)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(null);
    }


    public static <T> DfResponse<T> okEntity(int code, String message, T data) {
        return new DfResponse<T>()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    public static <T> DfResponse<T> ok(T data) {
        DfResponse<T> response = new DfResponse<>();
        response.setData(data);
        return response;
    }

    public static <T> DfResponse<T> badRequest(String message) {
        return new DfResponse<>(message, HttpStatus.BAD_REQUEST.value());
    }

    public static <T> DfResponse<T> serverError(String message) {
        return new DfResponse<>(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

