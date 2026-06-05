package com.spiritualfamily.backend.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.assessment.Option;

public interface OptionRepository
        extends JpaRepository<Option, Long> {
}