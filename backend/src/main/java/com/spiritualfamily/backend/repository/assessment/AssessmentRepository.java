package com.spiritualfamily.backend.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.assessment.Assessment;

public interface AssessmentRepository
        extends JpaRepository<Assessment, Long> {
}