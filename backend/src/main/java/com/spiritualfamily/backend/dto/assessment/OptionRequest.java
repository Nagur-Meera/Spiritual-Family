package com.spiritualfamily.backend.dto.assessment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequest {

    private String optionText;

    private Boolean correctAnswer;

    private Long questionId;
}