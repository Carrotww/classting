package com.classting.schoolnewsfeed.student.controller;

import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import com.classting.schoolnewsfeed.school.dto.SchoolResponse;
import com.classting.schoolnewsfeed.student.dto.RegisterStudentRequest;
import com.classting.schoolnewsfeed.student.service.StudentService;
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
@Tag(name = "Student", description = "학생 관련 기능 입니다. 요구사항에는 없었으나 학생을 간단히" +
        "생성하고 학생이 구독하고 있는 학교 목록을 가져올 수 있습니다")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/v1/student")
    @Operation(summary = "학생 생성하기", description = "학생을 이름으로 생성할 수 있습니다")
    public ResponseEntity<ApiResponse<Void>> registerStudent(@RequestBody RegisterStudentRequest request) {
        studentService.registerStudent(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/v1/student/{studentId}/subscribed-schools")
    @Operation(summary = "학생이 구독하고 있는 학교 목록 가져오기", description = "학생 id를 통해 학생이 구독하고있는" +
            "학교 목록을 가져올 수 있습니다.")
    public ResponseEntity<ApiResponse<List<SchoolResponse>>> getSubscribedSchools(@PathVariable Long studentId) {
        List<SchoolResponse> subscribedSchools = studentService.getSubscribedSchools(studentId);
        return ResponseEntity.ok(ApiResponse.success(subscribedSchools));
    }
}
