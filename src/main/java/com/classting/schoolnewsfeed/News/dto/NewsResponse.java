package com.classting.schoolnewsfeed.News.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsResponse {
    @Schema(description = "news id", nullable = false, example = "1")
    private Long id;
    @Schema(description = "뉴스 제목", nullable = false, example = "뉴스 제목 입니다.")
    private String title;
    @Schema(description = "뉴스 내용", nullable = false, example = "뉴스 내용 입니다.")
    private String content;
    @Schema(description = "뉴스 생성 시간", nullable = false, example = "2024-04-05T21:38:30.269774")
    private LocalDateTime datePosted;
    @Schema(description = "뉴스 수정 시간", nullable = false, example = "2024-04-05T21:38:30.269774")
    private LocalDateTime lastModified;
}
