package com.classting.schoolnewsfeed.studentnewsfeed.entity;

import com.classting.schoolnewsfeed.News.entity.News;
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
@Table(name = "STUDENTNEWSFEED")
public class StudentNewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    private LocalDateTime addedToFeed;

    @PrePersist
    protected void onCreate() {
        addedToFeed = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
