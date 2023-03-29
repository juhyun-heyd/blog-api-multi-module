package com.heyd.blogapi.api.handler;

import com.heyd.blogapi.api.dto.ApiResult;
import com.heyd.blogapi.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ApiResult<?>> response(Throwable throwable, HttpStatus status) {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(ApiResult.error(throwable, status), headers, status);
    }

    @ExceptionHandler({
            IllegalStateException.class,
            IllegalArgumentException.class,
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        log.debug("BadRequest exception occurred: {}", e.getMessage(), e);

        return response(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);

        return response(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
