package com.classting.schoolnewsfeed.subscription.repository;

import com.classting.schoolnewsfeed.school.entity.School;
import com.classting.schoolnewsfeed.student.entity.Student;
import com.classting.schoolnewsfeed.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByStudentAndSchool(Student student, School school);
    boolean existsByStudentIdAndSchoolId(Long studentId, Long schoolId);
    List<Subscription> findByStudentId(Long studentId);
    boolean existsByStudentAndSchool(Student student, School school);
    List<Subscription> findBySchool(School school);
}
