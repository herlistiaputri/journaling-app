package com.spring.journalingapp.core;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private String status;
    private String message;
    private String errorMessage;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(String status, String message, String errorMessage, T data) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
        this.data = data;
    }

}
