package com.classting.schoolnewsfeed.global.dto;

import com.classting.schoolnewsfeed.global.error.ApplicationError;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String code;

    public static ErrorResponse fromException(ApplicationException e) {
        return new ErrorResponse(e.getMessage(), e.getCode());
    }

    public static ErrorResponse defaultError() {
        ApplicationError e = ApplicationError.INTERNAL_SERVER_ERROR;
        return new ErrorResponse(e.getMessage(), e.getCode());
    }
}
