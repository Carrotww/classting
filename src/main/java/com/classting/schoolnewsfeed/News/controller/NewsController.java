package com.classting.schoolnewsfeed.News.controller;

import com.classting.schoolnewsfeed.News.dto.NewsCreateRequest;
import com.classting.schoolnewsfeed.News.dto.NewsResponse;
import com.classting.schoolnewsfeed.News.dto.NewsUpdateRequest;
import com.classting.schoolnewsfeed.News.service.NewsService;
import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "News", description = "뉴스 생성, 수정, 삭제, 학생이 구독중인 학교 별 소식 가져오기")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "News 생성", description = "학교의 아이디와 News의 제목, 내용을 이용하여 특정 학교의 News" +
            "를 발행합니다.")
    @PostMapping("/v1/news")
    public ResponseEntity<ApiResponse<NewsResponse>> postNews(@RequestBody NewsCreateRequest request) {
        NewsResponse response = newsService.postNews(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/v1/news")
    @Operation(summary = "News 수정", description = "수정하고 싶은 news id 를 가지고 news 의 제목 또는 내용 을" +
            "수정할 수 있습니다")
    public ResponseEntity<ApiResponse<NewsResponse>> updateNews(@RequestBody NewsUpdateRequest request) {
        NewsResponse response = newsService.updateNews(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/v1/news/{newsId}")
    @Operation(summary = "News 삭제", description = "newsId를 통해 news를 삭제할 수 있습니다")
    public ResponseEntity<ApiResponse<Void>> deleteNews(@PathVariable Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/v1/news/subscribed-school-news/{studentId}/{schoolId}")
    @Operation(summary = "학생이 구독중중인 학교 페이지별 소식", description = "학생은" +
            "구독중인 학교 페이지별 소식을 볼 수 있습니다.")
    public ResponseEntity<ApiResponse<List<NewsResponse>>> getSubscribedSchoolNews(
            @PathVariable Long studentId,
            @PathVariable Long schoolId) {
        List<NewsResponse> schoolNews = newsService.getSubscribedSchoolNews(studentId, schoolId);
        return ResponseEntity.ok(ApiResponse.success(schoolNews));
    }

}
