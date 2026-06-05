package com.spiritualfamily.backend.controller.admin;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import com.spiritualfamily.backend.dto.admin.AdminStatsResponse;
import com.spiritualfamily.backend.dto.admin.AdminUserResponse;
import com.spiritualfamily.backend.dto.admin.CreateModeratorRequest;
import com.spiritualfamily.backend.dto.admin.SystemSettingRequest;
import com.spiritualfamily.backend.dto.admin.UpdateUserRoleRequest;
import com.spiritualfamily.backend.entity.admin.AdminLog;
import com.spiritualfamily.backend.entity.admin.SystemSetting;
import com.spiritualfamily.backend.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService service;

    @PostMapping("/settings")
    public SystemSetting createSetting(
            @RequestBody SystemSettingRequest request
    ) {

        return service.createSetting(request);
    }

    @GetMapping("/settings")
    public List<SystemSetting> getSettings() {

        return service.getSettings();
    }

    @GetMapping("/logs")
    public List<AdminLog> getLogs() {

        return service.getLogs();
    }

    @GetMapping("/stats")
    public AdminStatsResponse getStats() {

        return service.getStats();
    }

    @GetMapping("/users")
    public List<AdminUserResponse> getUsers() {

        return service.getUsers();
    }

    @PatchMapping("/users/{id}/role")
    public AdminUserResponse updateUserRole(
            @PathVariable Long id,
            @RequestBody UpdateUserRoleRequest request
    ) {

        return service.updateUserRole(id, request);
    }

    @PatchMapping("/users/{id}/block")
    public AdminUserResponse blockUser(@PathVariable Long id) {

        return service.blockUser(id);
    }

    @PatchMapping("/users/{id}/unblock")
    public AdminUserResponse unblockUser(@PathVariable Long id) {

        return service.unblockUser(id);
    }

    @PostMapping("/users/moderators")
    public AdminUserResponse createModerator(@RequestBody CreateModeratorRequest request) {

        return service.createModerator(request);
    }

    @DeleteMapping("/settings/{id}")
    public String deleteSetting(
            @PathVariable Long id
    ) {

        service.deleteSetting(id);

        return "Setting Deleted";
    }
}