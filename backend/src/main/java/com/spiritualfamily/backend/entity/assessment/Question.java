package com.spiritualfamily.backend.entity.assessment;

import com.spiritualfamily.backend.entity.enums.QuestionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String englishQuestion;

    @Column(length = 3000)
    private String teluguQuestion;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String imageUrl;

    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
}