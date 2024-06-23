package com.test.tech.utils;

import com.test.tech.entity.dto.response.DataResponse;

public class ResponseUtils {
    private ResponseUtils(){}

    public static <T> DataResponse<T> toResponse(int code, String message, T data){
        return DataResponse.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
