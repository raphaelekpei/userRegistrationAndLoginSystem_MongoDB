package com.raphael.usersystem.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// this class is a payload containing exception details
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIErrorResponse {
    private boolean isSuccessful;
    private String message;
    private LocalDateTime timeStamp;
    /* This enables us add field errors to the response if the field is NotNull.
        If the field is null we are not going to add it to the error response */
    private List<String> fieldErrors = new ArrayList<>();

}
