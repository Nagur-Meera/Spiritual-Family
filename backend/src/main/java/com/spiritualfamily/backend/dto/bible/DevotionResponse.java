package com.spiritualfamily.backend.dto.bible;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevotionResponse {

    private Long id;

    private String title;

    private String content;

    private String language;
}