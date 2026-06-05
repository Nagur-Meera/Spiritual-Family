package com.spiritualfamily.backend.dto.assessment;

import java.time.LocalDateTime;

import com.spiritualfamily.backend.entity.enums.AssessmentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentRequest {

    private String title;

    private String description;

    private AssessmentType type;

    private LocalDateTime submissionDeadline;
}