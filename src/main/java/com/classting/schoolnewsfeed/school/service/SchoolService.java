package com.classting.schoolnewsfeed.school.service;

import com.classting.schoolnewsfeed.school.dto.SchoolCreateRequest;
import com.classting.schoolnewsfeed.school.dto.SchoolResponse;
import com.classting.schoolnewsfeed.school.entity.School;
import com.classting.schoolnewsfeed.school.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolResponse createSchool(SchoolCreateRequest request) {
        School school = School.builder()
                .name(request.getName())
                .region(request.getRegion())
                .build();
        school = schoolRepository.save(school);

        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .region(school.getRegion())
                .build();
    }
}
