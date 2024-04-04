package com.classting.schoolnewsfeed.News.repository;

import com.classting.schoolnewsfeed.News.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
