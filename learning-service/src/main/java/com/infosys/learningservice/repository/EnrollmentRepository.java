package com.infosys.learningservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.learningservice.entity.Enrollment;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByEmployeeId(Long employeeId);

    List<Enrollment> findByCourseId(Long courseId);
}