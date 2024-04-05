package com.classting.schoolnewsfeed.studentnewsfeed.repository;

import com.classting.schoolnewsfeed.studentnewsfeed.entity.StudentNewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentNewsFeedRepository extends JpaRepository<StudentNewsFeed, Long> {
    List<StudentNewsFeed> findByStudentIdOrderByAddedToFeedDesc(Long studentId);
}

