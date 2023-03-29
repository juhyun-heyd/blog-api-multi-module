package com.heyd.blogapi.exception;

public class WebClientBadRequestException extends BusinessException {

    public WebClientBadRequestException(int statusCode, String message) {
        super(statusCode, message);
    }
}
