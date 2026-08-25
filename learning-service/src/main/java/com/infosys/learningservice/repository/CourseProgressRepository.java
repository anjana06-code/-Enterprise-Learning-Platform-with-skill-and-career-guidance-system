package com.infosys.learningservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.learningservice.entity.CourseProgress;

public interface CourseProgressRepository
        extends JpaRepository<CourseProgress, Long> {

    List<CourseProgress> findByEnrollmentId(Long enrollmentId);
}