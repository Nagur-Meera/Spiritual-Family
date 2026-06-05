package com.spiritualfamily.backend.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.admin.SystemSetting;

public interface SystemSettingRepository
        extends JpaRepository<SystemSetting, Long> {
}