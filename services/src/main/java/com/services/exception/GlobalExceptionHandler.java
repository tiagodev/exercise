package com.services.exception;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handle(Exception e, WebRequest request) {
		String responseBody = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
