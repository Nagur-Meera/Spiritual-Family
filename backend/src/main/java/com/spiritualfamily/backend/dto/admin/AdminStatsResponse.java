package com.spiritualfamily.backend.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStatsResponse {

    private Long totalUsers;

    private Long totalEvents;

    private Long totalPrayers;

    private Long totalAnnouncements;
}