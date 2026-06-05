// ======================================================
// repository/announcement/AnnouncementFileRepository.java
// ======================================================

package com.spiritualfamily.backend.repository.announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.announcement.AnnouncementFile;

public interface AnnouncementFileRepository
        extends JpaRepository<AnnouncementFile, Long> {
}