package com.classting.schoolnewsfeed.subscription.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionResponse {
    private Long subscriptionId;
    private Long studentId;
    private Long schoolId;
}
