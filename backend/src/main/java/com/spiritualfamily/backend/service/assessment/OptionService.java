package com.spiritualfamily.backend.service.assessment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.assessment.OptionRequest;
import com.spiritualfamily.backend.dto.assessment.OptionResponse;
import com.spiritualfamily.backend.entity.assessment.Option;
import com.spiritualfamily.backend.entity.assessment.Question;
import com.spiritualfamily.backend.repository.assessment.OptionRepository;
import com.spiritualfamily.backend.repository.assessment.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

    private final QuestionRepository questionRepository;

    public OptionResponse create(
            OptionRequest request
    ) {

        Question question =
                questionRepository.findById(
                        request.getQuestionId()
                ).orElseThrow();

        Option option =
                Option.builder()
                        .question(question)
                        .optionText(
                                request.getOptionText()
                        )
                        .correctAnswer(
                                request.getCorrectAnswer()
                        )
                        .build();

        option =
                optionRepository.save(option);

        return map(option);
    }

    public List<OptionResponse> getAll() {

        return optionRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private OptionResponse map(
            Option option
    ) {

        return OptionResponse.builder()
                .id(option.getId())
                .questionId(
                        option.getQuestion().getId()
                )
                .optionText(
                        option.getOptionText()
                )
                .correct(
                        option.getCorrectAnswer()
                )
                .build();
    }
}