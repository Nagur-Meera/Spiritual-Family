package com.spiritualfamily.backend.entity.bible;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bible_verses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibleVerse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String verseReference;

    @Column(length = 5000)
    private String verseText;

    private LocalDate verseDate;
}