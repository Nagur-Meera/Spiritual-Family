package com.spiritualfamily.backend.service.assessment;

import com.spiritualfamily.backend.dto.assessment.*;
import com.spiritualfamily.backend.entity.assessment.*;
import com.spiritualfamily.backend.repository.assessment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AssessmentRepository assessmentRepository;

    public QuestionResponse create(
            QuestionRequest request
    ) {

        Assessment assessment =
                assessmentRepository.findById(
                        request.getAssessmentId()
                ).orElseThrow();

        Question question =
                Question.builder()
                        .assessment(assessment)
                        .englishQuestion(
                                request.getEnglishQuestion()
                        )
                        .teluguQuestion(
                                request.getTeluguQuestion()
                        )
                        .questionType(
                                request.getQuestionType()
                        )
                        .imageUrl(
                                request.getImageUrl()
                        )
                        .correctAnswer(
                                request.getCorrectAnswer()
                        )
                        .build();

        question = questionRepository.save(question);

        return map(question);
    }

    public List<QuestionResponse> findAll() {

        return questionRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public QuestionResponse findById(Long id) {

        return map(
                questionRepository.findById(id)
                        .orElseThrow()
        );
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionResponse map(
            Question question
    ) {

        return QuestionResponse.builder()
                .id(question.getId())
                .assessmentId(
                        question.getAssessment().getId()
                )
                .englishQuestion(
                        question.getEnglishQuestion()
                )
                .teluguQuestion(
                        question.getTeluguQuestion()
                )
                .questionType(
                        question.getQuestionType()
                )
                .imageUrl(
                        question.getImageUrl()
                )
                .correctAnswer(
                        question.getCorrectAnswer()
                )
                .build();
    }
}