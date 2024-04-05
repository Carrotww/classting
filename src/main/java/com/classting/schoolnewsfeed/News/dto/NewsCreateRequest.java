package com.classting.schoolnewsfeed.News.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsCreateRequest {
    @Schema(description = "school id", nullable = false, example = "1")
    private Long schoolId;
    @Schema(description = "뉴스 제목", nullable = false, example = "클래스팅 기사 제목")
    private String title;
    @Schema(description = "뉴스 내용", nullable = false, example = "뉴스 내용 입니다")
    private String content;
}
