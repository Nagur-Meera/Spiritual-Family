package com.spiritualfamily.backend.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.assessment.Answer;

public interface AnswerRepository
        extends JpaRepository<Answer, Long> {
}