package com.classting.schoolnewsfeed.student.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterStudentRequest {
    private String name;
}
