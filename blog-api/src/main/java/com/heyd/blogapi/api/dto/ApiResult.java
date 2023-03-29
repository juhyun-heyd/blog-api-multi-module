package com.heyd.blogapi.api.dto;

import org.springframework.http.HttpStatus;

public class ApiResult<T> {

    private final boolean success;
    private final T response;
    private final ApiError errors;

    private ApiResult(boolean success, T response, ApiError errors) {
        this.success = success;
        this.response = response;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getErrors() {
        return errors;
    }

    public static <T> ApiResult<T> ok(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }
}
