package com.classting.schoolnewsfeed.studentnewsfeed.service;

import com.classting.schoolnewsfeed.global.error.ApplicationError;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import com.classting.schoolnewsfeed.News.dto.NewsResponse;
import com.classting.schoolnewsfeed.studentnewsfeed.entity.StudentNewsFeed;
import com.classting.schoolnewsfeed.studentnewsfeed.repository.StudentNewsFeedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentNewsFeedService {

    private final StudentNewsFeedRepository studentNewsFeedRepository;

    public List<NewsResponse> getStudentNewsFeed(Long studentId) {
        List<StudentNewsFeed> newsFeedItems = studentNewsFeedRepository.findByStudentIdOrderByAddedToFeedDesc(studentId);

        if (newsFeedItems.isEmpty()) {
            throw new ApplicationException(ApplicationError.SUBSCRIPTION_NOT_FOUND_ERROR);
        }

        return newsFeedItems.stream()
                .map(item -> NewsResponse.builder()
                        .id(item.getNews().getId())
                        .title(item.getNews().getTitle())
                        .content(item.getNews().getContent())
                        .datePosted(item.getNews().getDatePosted())
                        .lastModified(item.getNews().getLastModified())
                        .build())
                .collect(Collectors.toList());
    }
}

