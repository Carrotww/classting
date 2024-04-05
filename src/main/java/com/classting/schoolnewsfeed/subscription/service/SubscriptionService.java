package com.classting.schoolnewsfeed.subscription.service;

import com.classting.schoolnewsfeed.global.error.ApplicationError;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import com.classting.schoolnewsfeed.school.entity.School;
import com.classting.schoolnewsfeed.school.repository.SchoolRepository;
import com.classting.schoolnewsfeed.student.entity.Student;
import com.classting.schoolnewsfeed.student.repository.StudentRepository;
import com.classting.schoolnewsfeed.subscription.dto.SubscriptionResponse;
import com.classting.schoolnewsfeed.subscription.entity.Subscription;
import com.classting.schoolnewsfeed.subscription.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    public SubscriptionResponse subscribeSchool(Long studentId, Long schoolId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApplicationException(ApplicationError.STUDENT_NOT_FOUND_ERROR));
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ApplicationException(ApplicationError.SCHOOL_NOT_FOUND_ERROR));

        boolean isAlreadySubscribed = subscriptionRepository.existsByStudentAndSchool(student, school);
        if (isAlreadySubscribed) {
            throw new ApplicationException(ApplicationError.ALREADY_SUBSCRIBED_ERROR);
        }
        Subscription subscription = Subscription.builder()
                .student(student)
                .school(school)
                .build();

        subscription = subscriptionRepository.save(subscription);

        return SubscriptionResponse.builder()
                .subscriptionId(subscription.getId())
                .studentId(studentId)
                .schoolId(schoolId)
                .build();
    }


    public void unSubscribeSchool(Long studentId, Long schoolId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApplicationException(ApplicationError.STUDENT_NOT_FOUND_ERROR));
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ApplicationException(ApplicationError.SCHOOL_NOT_FOUND_ERROR));

        Subscription subscription = subscriptionRepository.findByStudentAndSchool(student, school)
                .orElseThrow(() -> new ApplicationException(ApplicationError.SUBSCRIPTION_NOT_FOUND_ERROR));

        subscriptionRepository.delete(subscription);
    }


}
