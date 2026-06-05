package com.spiritualfamily.backend.dto.bible;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BibleVerseRequest {

    private String verseReference;

    private String verseText;

    private LocalDate verseDate;
}