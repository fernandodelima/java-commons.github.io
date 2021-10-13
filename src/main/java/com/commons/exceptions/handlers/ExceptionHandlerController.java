package com.commons.exceptions.handlers;

import com.commons.exceptions.BaseApiException;
import com.commons.exceptions.ExceptionMessage;
import com.commons.exceptions.bundle.InternalServerApiException;
import com.commons.logger.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity handleBaseApiException(BaseApiException bae, WebRequest request) {
        ExceptionMessage em = new ExceptionMessage(bae);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(bae, em, httpHeaders, HttpStatus.valueOf(bae.getStatusCode()), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeExceptionException(RuntimeException ex, WebRequest request) {
        Logger.error(this, String.format("Unexpected Runtime exception during request processing on %s", ex.getStackTrace()[0]), ex);
        return handleBaseApiException(new InternalServerApiException(ex), request);
    }
}
