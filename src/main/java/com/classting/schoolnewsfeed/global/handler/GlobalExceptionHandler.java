package com.classting.schoolnewsfeed.global.handler;

import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import com.classting.schoolnewsfeed.global.dto.ErrorResponse;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleApplicationException(ApplicationException e) {
        log.error("handleApplicationException : ", e);
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode());
        ApiResponse<ErrorResponse> response = ApiResponse.failure(errorResponse);
        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleException(Exception e) {
        log.error("Unexpected server error : ", e);
        ErrorResponse errorResponse = ErrorResponse.defaultError();
        ApiResponse<ErrorResponse> response = ApiResponse.failure(errorResponse);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
