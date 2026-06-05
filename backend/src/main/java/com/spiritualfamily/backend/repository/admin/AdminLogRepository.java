package com.spiritualfamily.backend.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.admin.AdminLog;

public interface AdminLogRepository
        extends JpaRepository<AdminLog, Long> {
}