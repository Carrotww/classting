package com.classting.schoolnewsfeed.studentnewsfeed.controller;

import com.classting.schoolnewsfeed.News.dto.NewsResponse;
import com.classting.schoolnewsfeed.studentnewsfeed.service.StudentNewsFeedService;
import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "StudentNewsFeed", description = "학생 별 뉴스 피드를 관리하기 위한 기능입니다.")
public class StudentNewsFeedController {
    private final StudentNewsFeedService studentNewsFeedService;

    @GetMapping("/v1/student-news-feed/{studentId}")
    @Operation(summary = "학생 뉴스 피드 가져오기", description = "특정 학생의 뉴스 피드를 보여줍니다" +
            "구독한 학교의 뉴스 피드를 가져오며 구독하기 이전의 뉴스는 가져오지 않습니다.")
    public ResponseEntity<ApiResponse<List<NewsResponse>>> getStudentNewsFeed(@PathVariable Long studentId) {
        List<NewsResponse> newsFeed = studentNewsFeedService.getStudentNewsFeed(studentId);
        return ResponseEntity.ok(ApiResponse.success(newsFeed));
    }
}
