package com.spiritualfamily.backend.dto.assessment;

import com.spiritualfamily.backend.entity.enums.AssessmentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResponse {

    private Long id;
    private String title;
    private String description;
    private AssessmentType type;
    private LocalDateTime deadline;
    private Boolean active;
}