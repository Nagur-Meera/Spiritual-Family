package com.spiritualfamily.backend.dto.assessment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionRequest {

    private Long assessmentId;

    private Long userId;

    private Long questionId;

    private String answerText;
}