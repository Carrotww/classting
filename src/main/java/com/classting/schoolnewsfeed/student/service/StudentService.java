package com.classting.schoolnewsfeed.student.service;

import com.classting.schoolnewsfeed.global.error.ApplicationError;
import com.classting.schoolnewsfeed.global.error.ApplicationException;
import com.classting.schoolnewsfeed.school.dto.SchoolResponse;
import com.classting.schoolnewsfeed.student.dto.RegisterStudentRequest;
import com.classting.schoolnewsfeed.student.entity.Student;
import com.classting.schoolnewsfeed.student.repository.StudentRepository;
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
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubscriptionRepository subscriptionRepository;

    public void registerStudent(RegisterStudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .build();
        student = studentRepository.save(student);
    }

    public List<SchoolResponse> getSubscribedSchools(Long studentId) {
        List<Subscription> subscriptions = subscriptionRepository.findByStudentId(studentId);

        if (subscriptions.isEmpty()) {
            throw new ApplicationException(ApplicationError.SUBSCRIPTION_NOT_FOUND_ERROR);
        }

        return subscriptions.stream()
                .map(subscription -> SchoolResponse.builder()
                        .id(subscription.getSchool().getId())
                        .name(subscription.getSchool().getName())
                        .region(subscription.getSchool().getRegion())
                        .build())
                .collect(Collectors.toList());
    }
}
