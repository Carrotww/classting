package com.classting.schoolnewsfeed.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationError {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_001", "서버 내부 에러가 발생하였습니다."),
    SCHOOL_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "SCHOOL_001", "해당 학교를 찾을 수 없습니다."),
    NEWS_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "NEWS_001", "해당 뉴스를 찾을 수 없습니다."),
    STUDENT_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "STUDENT_001", "해당 학생을 찾을 수 없습니다."),
    NEWS_FEED_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "NEWS_FEED_001", "학생이 구독한 뉴스 피드를 찾을 수 없습니다."),
    SUBSCRIPTION_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "SUBSCRIPTION_001", "해당 구독 정보를 찾을 수 없습니다."),
    ALREADY_SUBSCRIBED_ERROR(HttpStatus.BAD_REQUEST, "SUBSCRIPTION_002", "이미 구독 상태입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
