package com.classting.schoolnewsfeed.school.repository;

import com.classting.schoolnewsfeed.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
