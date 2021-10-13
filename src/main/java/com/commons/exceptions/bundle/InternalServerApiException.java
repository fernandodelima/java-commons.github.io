package com.commons.exceptions.bundle;

import com.commons.exceptions.BaseApiException;

public class InternalServerApiException extends BaseApiException {
    public InternalServerApiException(String message) {
        this("internal_server_error", message, (Throwable)null);
    }

    public InternalServerApiException(Throwable cause) {
        this("internal_server_error", "Internal Server Error", cause);
    }

    public InternalServerApiException(String message, Throwable cause) {
        this("internal_server_error", message, cause);
    }

    public InternalServerApiException(String errorCode, String message, Throwable cause) {
        super(500, errorCode, message, cause);
    }
}
