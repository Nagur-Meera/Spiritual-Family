package com.spiritualfamily.backend.dto.prayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrayerNoteDto {

    private String note;

    private Long prayerRequestId;
}