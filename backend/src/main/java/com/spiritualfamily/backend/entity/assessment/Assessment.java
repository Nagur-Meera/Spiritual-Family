package com.spiritualfamily.backend.entity.assessment;

import com.spiritualfamily.backend.entity.enums.AssessmentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assessments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private AssessmentType type;

        private LocalDateTime submissionDeadline;

    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();

    @OneToMany(mappedBy = "assessment",
            cascade = CascadeType.ALL)
    private List<Question> questions;
}