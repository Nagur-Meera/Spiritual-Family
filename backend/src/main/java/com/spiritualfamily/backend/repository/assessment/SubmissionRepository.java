package com.spiritualfamily.backend.repository.assessment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.assessment.Submission;

public interface SubmissionRepository
        extends JpaRepository<Submission, Long> {

        Optional<Submission> findByUserIdAndAssessmentId(Long userId, Long assessmentId);

        boolean existsByUserIdAndAssessmentId(Long userId, Long assessmentId);
}