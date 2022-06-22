package com.hust.movie_review.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DfResponseList<T> {
    private Integer code;
    private String message;

    private int total;
    private List<T> data;

    public DfResponseList() {
        code = 0;
        message = "OK";
    }

    public DfResponseList(String message) {
        this.code = 0;
        this.message = message;
    }

    public DfResponseList(String message, List<T> result, int total) {
        this.code = 0;
        this.message = message;
        this.total = total;
        this.data = result;
    }

    public DfResponseList(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    public static <T> DfResponseList<T> okEntity(List<T> body, int total) {
        return DfResponseList.ok(body, total);
    }

    public static <T> DfResponseList<T> ok(List<T> body, int total) {
        return new DfResponseList<>("OK", body, total);
    }

    public static DfResponse<String> badRequest(String message) {
        return new DfResponse<>(message, HttpStatus.BAD_REQUEST.value());
    }
}
