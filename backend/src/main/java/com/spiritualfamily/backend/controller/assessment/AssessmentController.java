package com.spiritualfamily.backend.controller.assessment;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.spiritualfamily.backend.service.assessment.AssessmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService service;

    @PostMapping
    public Assessment createAssessment(
            @RequestBody AssessmentRequest request
    ) {

        return service.createAssessment(request);
    }

    @PostMapping("/questions")
    public Question createQuestion(
            @RequestBody QuestionRequest request
    ) {

        return service.createQuestion(request);
    }

    @PostMapping("/options")
    public Option createOption(
            @RequestBody OptionRequest request
    ) {

        return service.createOption(request);
    }

    @PostMapping("/submissions")
    public Submission createSubmission(
            @RequestBody SubmissionRequest request
    ) {

        return service.createSubmission(request);
    }

    @PostMapping("/answers")
    public Answer createAnswer(
            @RequestBody AnswerRequest request
    ) {

        return service.createAnswer(request);
    }

    @GetMapping
    public List<Assessment> getAssessments() {

        return service.getAssessments();
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {

        return service.getQuestions();
    }

    @GetMapping("/submissions")
    public List<Submission> getSubmissions() {

        return service.getSubmissions();
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(
            @PathVariable Long id
    ) {

        service.deleteAssessment(id);

        return "Assessment Deleted";
    }
}