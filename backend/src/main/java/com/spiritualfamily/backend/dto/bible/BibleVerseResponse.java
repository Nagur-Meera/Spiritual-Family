package com.spiritualfamily.backend.dto.bible;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibleVerseResponse {

    private Long id;

    private String referenceVerse;

    private String verseText;

    private String language;
}