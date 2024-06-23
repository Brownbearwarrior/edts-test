package com.test.tech.entity.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataResponse<T> {
    private int code;
    private String message;
    private T data;
}
