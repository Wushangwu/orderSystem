package com.example.order.web.Response;

import lombok.Data;

@Data
public class ErrorResponseModel {

    private String error;

    public ErrorResponseModel(String errorMsg){
        this.error = errorMsg;
    }








}
