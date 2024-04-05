package com.classting.schoolnewsfeed.subscription.controller;

import com.classting.schoolnewsfeed.global.dto.ApiResponse;
import com.classting.schoolnewsfeed.subscription.dto.SubscriptionResponse;
import com.classting.schoolnewsfeed.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Subscription", description = "학생과 학교의 구독 정보를 관리하는 기능")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/v1/subscribe")
    @Operation(summary = "학생이 특정 학교 구독하기", description = "학생의 아이디로 원하는 학교를" +
            "구독할 수 있습니다. 중복은 구독은 불가능 합니다.")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> subscribeSchool(
            @RequestParam Long studentId,
            @RequestParam Long schoolId) {
        SubscriptionResponse response = subscriptionService.subscribeSchool(studentId, schoolId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/v1/subscribe")
    @Operation(summary = "학생이 특정 학교 구독 취소하기", description = "학생의 아이디로 원하는 구독중인 학교를" +
            "구독 취소합니다.")
    public ResponseEntity<ApiResponse<Void>> unSubscribeSchool(
            @RequestParam Long studentId,
            @RequestParam Long schoolId) {
        subscriptionService.unSubscribeSchool(studentId, schoolId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
