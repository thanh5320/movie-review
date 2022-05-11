package com.hust.movie_review.data.request.category;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class StoreRequest {
    @NotEmpty(message = "Trường tên nhãn không được bỏ trống")
    private String name;

    @NotEmpty(message = "Trường mô tả nhãn không được bỏ trống")
    private String description;
}
