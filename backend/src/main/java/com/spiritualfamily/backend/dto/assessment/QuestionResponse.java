package com.spiritualfamily.backend.dto.assessment;

import com.spiritualfamily.backend.entity.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;

    private Long assessmentId;

    private String englishQuestion;

    private String teluguQuestion;

    private QuestionType questionType;

    private String imageUrl;

    private String correctAnswer;
}