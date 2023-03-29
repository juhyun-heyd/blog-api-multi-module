package com.heyd.blogapi.exception;

public class DistributedLockException extends BusinessException {

    public DistributedLockException(int statusCode, String message) {
        super(statusCode, message);
    }
}
