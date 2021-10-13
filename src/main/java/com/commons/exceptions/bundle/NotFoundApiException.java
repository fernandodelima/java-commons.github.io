package com.commons.exceptions.bundle;

import com.commons.exceptions.BaseApiException;

public class NotFoundApiException extends BaseApiException {
    public NotFoundApiException(Integer statusCode, String errorCode, String message) {
        super(statusCode, errorCode, message);
    }

    public NotFoundApiException(Integer statusCode, String errorCode, String message, Throwable cause) {
        super(statusCode, errorCode, message, cause);
    }

    public NotFoundApiException(String errorCode, String message, Throwable cause) {
        this(404, errorCode, message, cause);
    }

    public NotFoundApiException(String message, Throwable cause) {
        this("Not Found", message, cause);
    }

    public NotFoundApiException(String message) {
        this(message, null);
    }
}
