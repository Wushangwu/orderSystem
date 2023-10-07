package com.example.order.web.Response;

import lombok.Data;
import lombok.Getter;


@Getter
public enum ResponseEnum {

    SUCCESS(200, "success"),
    VALIDATE_FAILED(500, "parameter error"),
    COMMON_FAILED(500, "error");

    private final Integer code;
    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
