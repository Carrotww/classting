package com.classting.schoolnewsfeed.News.entity;

import com.classting.schoolnewsfeed.school.entity.School;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    private LocalDateTime datePosted;
    private LocalDateTime lastModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @PrePersist
    protected void onCreate() {
        datePosted = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public void updateNewsTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
        this.lastModified = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}