package com.pessoa.controller.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp,
    String path,
    int status,
    String error,
    String message
) {
    
}
