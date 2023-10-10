package com.example.order.web.Response;

import lombok.Data;

@Data
public class ResponseModel {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseModel(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
    }

    public ResponseModel(ResponseEnum responseEnum, Object data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
        this.data = data;
    }

    public static ResponseModel defaultOk(Object data) {
        return new ResponseModel(ResponseEnum.SUCCESS,data);
    }







}
