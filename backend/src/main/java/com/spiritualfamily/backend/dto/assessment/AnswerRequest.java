package com.spiritualfamily.backend.dto.assessment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    private String answerText;

    private Long submissionId;

    private Long questionId;
}