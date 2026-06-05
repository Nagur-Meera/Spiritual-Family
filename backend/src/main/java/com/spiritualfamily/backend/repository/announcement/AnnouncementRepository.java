// ======================================================
// repository/announcement/AnnouncementRepository.java
// ======================================================

package com.spiritualfamily.backend.repository.announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.announcement.Announcement;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {
}