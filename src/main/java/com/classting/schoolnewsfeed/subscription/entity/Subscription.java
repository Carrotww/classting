package com.classting.schoolnewsfeed.subscription.entity;

import com.classting.schoolnewsfeed.school.entity.School;
import com.classting.schoolnewsfeed.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private LocalDateTime subscribedDate;

    @PrePersist
    protected void onCreate() {
        subscribedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}