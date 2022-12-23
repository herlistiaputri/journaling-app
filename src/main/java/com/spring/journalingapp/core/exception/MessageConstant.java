package com.spring.journalingapp.core.exception;

public enum MessageConstant {

    USER_NOT_FOUND(4000, "Request Failed", "User not found"),
    POST_NOT_FOUND(4000, "Request Failed", "Post not found"),
    PICTURE_NOT_FOUND(4000, "Request Failed", "Picture not found"),
    EXPIRED_JWT_TOKEN(4000, "Request Failed", "Expired jwt token");

    private Integer statusCode;
    private String message;
    private String errorMessage;

    private MessageConstant(Integer statusCode, String message, String errorMessage) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void prependErrorMessage(String message) {
        this.message = message + " " + this.message;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
