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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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
/*
NoHandlerFoundException is thrown when a requested API service cannot be found
By default when the DispatcherServlet can't find a handler for a request it sends a 404 response.
 */

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
/*
MethodArgumentNotValidException is thrown when an argument annotated with @Valid failed validation
 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> details = new ArrayList<>();
        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            details.add(error.getDefaultMessage());
        }
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
                .isSuccessful(false)
                .timeStamp(LocalDateTime.now())
                .message(methodArgumentNotValidException.getMessage())
                .fieldErrors(details)
                .build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
