package com.hust.movie_review.data.request.category;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UpdateRequest {
    @NotNull(message = "Trường id không được bỏ trống")
    private int id;

    @NotEmpty(message = "Trường tên nhãn không được bỏ trống")
    private String name;

    @NotEmpty(message = "Trường mô tả nhãn không được bỏ trống")
    private String description;
}
