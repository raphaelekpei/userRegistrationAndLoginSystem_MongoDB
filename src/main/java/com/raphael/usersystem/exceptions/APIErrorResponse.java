package com.raphael.usersystem.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private boolean isSuccessful;
    private String message;
    private LocalDateTime timeStamp;

}
