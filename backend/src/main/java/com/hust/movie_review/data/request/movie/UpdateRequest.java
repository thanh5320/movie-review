package com.hust.movie_review.data.request.movie;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UpdateRequest {
    @NotNull(message = "Id không được bỏ trống")
    private int id;

    @NotEmpty(message = "Tiêu đề không được bỏ trống")
    private String title;

    private int categoryId;

    @NotEmpty(message = "Quốc gia không được bỏ trống")
    private String countryCode;

    @NotEmpty(message = "Đạo diễn không được bỏ trống")
    private String director;

    @NotNull(message = "Năm không được bỏ trống")
    private int year;

    private String description;

    @NotNull(message = "Năm phát hành không được bỏ trống")
    private int releaseYear;

    @NotNull(message = "Trailer phim không được bỏ trống")
    private String trailer;
}
