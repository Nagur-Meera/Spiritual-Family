package com.spiritualfamily.backend.service.assessment;

import com.spiritualfamily.backend.dto.assessment.*;
import com.spiritualfamily.backend.entity.assessment.*;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.exception.BadRequestException;
import com.spiritualfamily.backend.repository.assessment.*;
import com.spiritualfamily.backend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final AssessmentRepository assessmentRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    private final AnswerRepository answerRepository;

    public SubmissionResponse submit(
            SubmissionRequest request
    ) {

        Assessment assessment =
                assessmentRepository.findById(
                        request.getAssessmentId()
                ).orElseThrow();

        if (assessment.getSubmissionDeadline() != null
                && assessment.getSubmissionDeadline().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Submission deadline has passed");
        }

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElseThrow();

        if (submissionRepository.existsByUserIdAndAssessmentId(
                request.getUserId(), request.getAssessmentId()
        )) {
            throw new BadRequestException("You have already submitted this assessment");
        }

        Question question =
                questionRepository.findById(
                        request.getQuestionId()
                ).orElseThrow();

        Submission submission =
                Submission.builder()
                        .assessment(assessment)
                        .user(user)
                        .score(0)
                        .submitted(true)
                        .submittedAt(
                                LocalDateTime.now()
                        )
                        .build();

        submission =
                submissionRepository.save(submission);

        Answer answer =
                Answer.builder()
                        .submission(submission)
                        .question(question)
                        .answerText(
                                request.getAnswerText()
                        )
                        .build();

        answerRepository.save(answer);

        Integer score = 0;

        if(question.getCorrectAnswer() != null &&
                question.getCorrectAnswer()
                        .equalsIgnoreCase(
                                request.getAnswerText()
                        )) {

            score = 1;
        }

        submission.setScore(score);

        submissionRepository.save(submission);

        return SubmissionResponse.builder()
                .submissionId(
                        submission.getId()
                )
                .score(score)
                .submitted(true)
                .build();
    }
}