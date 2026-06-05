package com.spiritualfamily.backend.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.assessment.Question;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {
}