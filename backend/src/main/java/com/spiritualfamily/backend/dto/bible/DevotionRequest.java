package com.spiritualfamily.backend.dto.bible;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevotionRequest {

    private String title;

    private String content;
}