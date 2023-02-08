package com.raphael.usersystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<Object>  userManagementExceptionHandler(UserManagementException userManagementException){
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
                .isSuccessful(false)
                .message(userManagementException.getMessage())
                .fieldErrors(null)
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> noHandlerFoundExceptionHandler(NoHandlerFoundException noHandlerFoundException){
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
                .isSuccessful(false)
                .message(noHandlerFoundException.getMessage())
                .timeStamp(LocalDateTime.now())
                .fieldErrors(null)
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errors = new ArrayList<>();
        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            errors.add(error.getDefaultMessage());
        }
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
                .isSuccessful(false)
                .timeStamp(LocalDateTime.now())
                .message(methodArgumentNotValidException.getMessage())
                .fieldErrors(errors)
                .build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
