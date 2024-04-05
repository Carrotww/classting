package com.classting.schoolnewsfeed.News.service;

import com.classting.schoolnewsfeed.News.dto.NewsCreateRequest;
import com.classting.schoolnewsfeed.News.dto.NewsResponse;
import com.classting.schoolnewsfeed.News.dto.NewsUpdateRequest;
import com.classting.schoolnewsfeed.News.entity.News;
import com.classting.schoolnewsfeed.News.repository.NewsRepository;
import com.classting.schoolnewsfeed.global.error.ApplicationError;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import com.classting.schoolnewsfeed.school.entity.School;
import com.classting.schoolnewsfeed.school.repository.SchoolRepository;
import com.classting.schoolnewsfeed.studentnewsfeed.entity.StudentNewsFeed;
import com.classting.schoolnewsfeed.studentnewsfeed.repository.StudentNewsFeedRepository;
import com.classting.schoolnewsfeed.subscription.entity.Subscription;
import com.classting.schoolnewsfeed.subscription.repository.SubscriptionRepository;
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
public class NewsService {

    private final NewsRepository newsRepository;
    private final SchoolRepository schoolRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final StudentNewsFeedRepository studentNewsFeedRepository;

    public NewsResponse postNews(NewsCreateRequest request) {
        School school = schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ApplicationException(ApplicationError.SCHOOL_NOT_FOUND_ERROR));

        News news = News.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .school(school)
                .build();

        news = newsRepository.save(news);

        List<Subscription> subscriptions = subscriptionRepository.findBySchool(school);
        for (Subscription subscription : subscriptions) {
            StudentNewsFeed studentNewsFeed = StudentNewsFeed.builder()
                    .student(subscription.getStudent())
                    .news(news)
                    .build();
            studentNewsFeedRepository.save(studentNewsFeed);
        }

        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .datePosted(news.getDatePosted())
                .build();
    }


    public NewsResponse updateNews(NewsUpdateRequest request) {
        News news = newsRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException(ApplicationError.NEWS_NOT_FOUND_ERROR));

        news.updateNewsTitleAndContent(request.getTitle(), request.getContent());

        news = newsRepository.save(news);

        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .datePosted(news.getDatePosted())
                .lastModified(news.getLastModified())
                .build();
    }


    public void deleteNews(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ApplicationException(ApplicationError.NEWS_NOT_FOUND_ERROR));

        newsRepository.delete(news);
    }

    public List<NewsResponse> getSubscribedSchoolNews(Long studentId, Long schoolId) {
        boolean isSubscribed = subscriptionRepository.existsByStudentIdAndSchoolId(studentId, schoolId);
        if (!isSubscribed) {
            throw new ApplicationException(ApplicationError.SUBSCRIPTION_NOT_FOUND_ERROR);
        }

        List<News> newsList = newsRepository.findBySchoolIdOrderByDatePostedDesc(schoolId);

        return newsList.stream()
                .map(news -> NewsResponse.builder()
                        .id(news.getId())
                        .title(news.getTitle())
                        .content(news.getContent())
                        .datePosted(news.getDatePosted())
                        .lastModified(news.getLastModified())
                        .build())
                .collect(Collectors.toList());
    }
}
