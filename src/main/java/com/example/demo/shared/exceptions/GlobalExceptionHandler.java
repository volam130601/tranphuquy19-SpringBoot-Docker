package com.example.demo.shared.exceptions;

import com.example.demo.shared.constants.ExceptionMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });
        Map<String, Object> message = new HashMap<>();
        message.put("timestamp", System.currentTimeMillis());
        message.put("status", HttpStatus.BAD_REQUEST.value());
        message.put("error", ExceptionMessages.BINDING_EXCEPTION);
        message.put("message", errors);
        message.put("path", ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
