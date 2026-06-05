// PrayerResponseDto.java

package com.spiritualfamily.backend.dto.prayer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrayerResponseDto {

    private Long id;
    private String title;
    private String requestText;
}