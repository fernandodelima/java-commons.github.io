package com.commons.exceptions;

import java.util.Collections;
import java.util.List;

public class ExceptionMessage {
    private Integer status;
    private String message;
    private String error;
    private List<String> cause;

    public ExceptionMessage() {
    }

    public ExceptionMessage(Integer status, String message, String error, List<String> cause) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.cause = cause;
    }

    public ExceptionMessage(BaseApiException bae) {
        this(bae.getStatusCode(), bae.getMessage(), bae.getErrorCode(), bae.getCause() != null ? Collections.singletonList(String.valueOf(bae.getCause())) : null);
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getCause() {
        return this.cause;
    }

    public void setCause(List<String> cause) {
        this.cause = cause;
    }
}
