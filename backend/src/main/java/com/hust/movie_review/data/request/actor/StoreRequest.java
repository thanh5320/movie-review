package com.hust.movie_review.data.request.actor;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class StoreRequest {
    @NotNull(message = "Tên của diễn viên không được để trống")
    String fullName;

    @NotNull(message = "Giới tính không được bỏ trống")
    String gender;

    @Min(value = 1950, message = "Năm sinh không không hợp lệ")
    @NotNull(message = "Năm sinh không được bỏ trống")
    int yearBirthday;

    @NotNull(message = "Mã quốc gia không được bỏ trống")
    String countryCode;
}
