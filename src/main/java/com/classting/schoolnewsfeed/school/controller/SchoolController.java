package com.classting.schoolnewsfeed.school.controller;

import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import com.classting.schoolnewsfeed.school.dto.SchoolCreateRequest;
import com.classting.schoolnewsfeed.school.dto.SchoolResponse;
import com.classting.schoolnewsfeed.school.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Schools", description = "학교 페이지 생성 기능 입니다. 학교 이름, 지역으로 생성 가능합니다")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/v1/schools")
    @Operation(summary = "학교 페이지 생성", description = "학교 페이지 생성 기능 입니다" +
            "학교 이름, 지역으로 학교 페이지를 생성합니다.")
    public ResponseEntity<ApiResponse<SchoolResponse>> createSchool(@RequestBody SchoolCreateRequest request) {
        SchoolResponse response = schoolService.createSchool(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
