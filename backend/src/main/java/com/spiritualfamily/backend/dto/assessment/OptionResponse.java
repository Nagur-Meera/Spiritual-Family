package com.spiritualfamily.backend.dto.assessment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionResponse {

    private Long id;

    private Long questionId;

    private String optionText;

    private Boolean correct;
}