package com.spiritualfamily.backend.service.assessment;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.assessment.AnswerRequest;
import com.spiritualfamily.backend.dto.assessment.AssessmentRequest;
import com.spiritualfamily.backend.dto.assessment.OptionRequest;
import com.spiritualfamily.backend.dto.assessment.QuestionRequest;
import com.spiritualfamily.backend.dto.assessment.SubmissionRequest;
import com.spiritualfamily.backend.entity.assessment.Answer;
import com.spiritualfamily.backend.entity.assessment.Assessment;
import com.spiritualfamily.backend.entity.assessment.Option;
import com.spiritualfamily.backend.entity.assessment.Question;
import com.spiritualfamily.backend.entity.assessment.Submission;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.exception.BadRequestException;
import com.spiritualfamily.backend.repository.assessment.AnswerRepository;
import com.spiritualfamily.backend.repository.assessment.AssessmentRepository;
import com.spiritualfamily.backend.repository.assessment.OptionRepository;
import com.spiritualfamily.backend.repository.assessment.QuestionRepository;
import com.spiritualfamily.backend.repository.assessment.SubmissionRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final SubmissionRepository submissionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public Assessment createAssessment(
            AssessmentRequest request
    ) {

        Assessment assessment =
                Assessment.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .type(request.getType())
                        .submissionDeadline(request.getSubmissionDeadline())
                        .build();

        return assessmentRepository.save(assessment);
    }

    public Question createQuestion(
            QuestionRequest request
    ) {

        Assessment assessment =
                assessmentRepository.findById(
                        request.getAssessmentId()
                ).orElseThrow();

        Question question =
                Question.builder()
                        .englishQuestion(request.getEnglishQuestion())
                        .teluguQuestion(request.getTeluguQuestion())
                        .questionType(request.getQuestionType())
                        .imageUrl(request.getImageUrl())
                        .correctAnswer(request.getCorrectAnswer())
                        .assessment(assessment)
                        .build();

        return questionRepository.save(question);
    }

    public Option createOption(
            OptionRequest request
    ) {

        Question question =
                questionRepository.findById(
                        request.getQuestionId()
                ).orElseThrow();

        Option option =
                Option.builder()
                        .optionText(request.getOptionText())
                        .correctAnswer(request.getCorrectAnswer())
                        .question(question)
                        .build();

        return optionRepository.save(option);
    }

    public Submission createSubmission(
            SubmissionRequest request
    ) {

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElseThrow();

        Assessment assessment =
                assessmentRepository.findById(
                        request.getAssessmentId()
                ).orElseThrow();

        if (assessment.getSubmissionDeadline() != null
                && assessment.getSubmissionDeadline().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Submission deadline has passed");
        }

        if (submissionRepository.existsByUserIdAndAssessmentId(
                request.getUserId(), request.getAssessmentId()
        )) {
            throw new BadRequestException("You have already submitted this assessment");
        }

        Submission submission =
                Submission.builder()
                        .user(user)
                        .assessment(assessment)
                        .build();

        return submissionRepository.save(submission);
    }

    public Answer createAnswer(
            AnswerRequest request
    ) {

        Submission submission =
                submissionRepository.findById(
                        request.getSubmissionId()
                ).orElseThrow();

        Question question =
                questionRepository.findById(
                        request.getQuestionId()
                ).orElseThrow();

        Answer answer =
                Answer.builder()
                        .answerText(request.getAnswerText())
                        .submission(submission)
                        .question(question)
                        .build();

        return answerRepository.save(answer);
    }

    public List<Assessment> getAssessments() {

        return assessmentRepository.findAll();
    }

    public List<Question> getQuestions() {

        return questionRepository.findAll();
    }

    public List<Submission> getSubmissions() {

        return submissionRepository.findAll();
    }

    public void deleteAssessment(Long id) {

        assessmentRepository.deleteById(id);
    }
}