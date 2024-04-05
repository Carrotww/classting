package com.classting.schoolnewsfeed.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolResponse {
    private Long id;
    @Schema(description = "학교 이름", nullable = false, example = "클래스팅대학")
    private String name;
    @Schema(description = "학교 지역", nullable = false, example = "서울")
    private String region;
}