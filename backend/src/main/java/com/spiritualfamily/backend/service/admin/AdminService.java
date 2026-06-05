package com.spiritualfamily.backend.service.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.admin.AdminStatsResponse;
import com.spiritualfamily.backend.dto.admin.AdminUserResponse;
import com.spiritualfamily.backend.dto.admin.CreateModeratorRequest;
import com.spiritualfamily.backend.dto.admin.SystemSettingRequest;
import com.spiritualfamily.backend.dto.admin.UpdateUserRoleRequest;
import com.spiritualfamily.backend.entity.admin.AdminLog;
import com.spiritualfamily.backend.entity.admin.SystemSetting;
import com.spiritualfamily.backend.entity.enums.RoleType;
import com.spiritualfamily.backend.entity.user.Role;
import com.spiritualfamily.backend.entity.user.User;
import com.spiritualfamily.backend.repository.admin.AdminLogRepository;
import com.spiritualfamily.backend.repository.admin.SystemSettingRepository;
import com.spiritualfamily.backend.repository.announcement.AnnouncementRepository;
import com.spiritualfamily.backend.repository.event.EventRepository;
import com.spiritualfamily.backend.repository.prayer.PrayerRequestRepository;
import com.spiritualfamily.backend.repository.user.RoleRepository;
import com.spiritualfamily.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminLogRepository adminLogRepository;

    private final SystemSettingRepository systemSettingRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final EventRepository eventRepository;

    private final PrayerRequestRepository prayerRepository;

    private final AnnouncementRepository announcementRepository;

    public AdminLog createLog(
            String action,
            String description
    ) {

        AdminLog log =
                AdminLog.builder()
                        .action(action)
                        .description(description)
                        .build();

        return adminLogRepository.save(log);
    }

    public SystemSetting createSetting(
            SystemSettingRequest request
    ) {

        SystemSetting setting =
                SystemSetting.builder()
                        .settingKey(request.getSettingKey())
                        .settingValue(request.getSettingValue())
                        .build();

        return systemSettingRepository.save(setting);
    }

    public List<SystemSetting> getSettings() {

        return systemSettingRepository.findAll();
    }

    public List<AdminLog> getLogs() {

        return adminLogRepository.findAll();
    }

    public AdminStatsResponse getStats() {

        return AdminStatsResponse.builder()
                .totalUsers(userRepository.count())
                .totalEvents(eventRepository.count())
                .totalPrayers(prayerRepository.count())
                .totalAnnouncements(
                        announcementRepository.count()
                )
                .build();
    }

    public List<AdminUserResponse> getUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> AdminUserResponse.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .emailVerified(Boolean.TRUE.equals(user.getEmailVerified()))
                        .active(Boolean.TRUE.equals(user.getActive()))
                        .roles(user.getRoles() == null ? Set.of() : user.getRoles()
                                .stream()
                                .map(role -> role.getName())
                                .collect(java.util.stream.Collectors.toSet()))
                        .build())
                .toList();
    }

    public AdminUserResponse updateUserRole(Long userId, UpdateUserRoleRequest request) {

        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findByName(request.getRole()).orElseThrow();

        user.setRoles(Set.of(role));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        createLog("UPDATE_ROLE", "Updated role for user " + user.getEmail() + " to " + request.getRole());

        return toResponse(user);
    }

    public AdminUserResponse blockUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        createLog("BLOCK_USER", "Blocked user " + user.getEmail());

        return toResponse(user);
    }

    public AdminUserResponse unblockUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        createLog("UNBLOCK_USER", "Unblocked user " + user.getEmail());

        return toResponse(user);
    }

    public AdminUserResponse createModerator(CreateModeratorRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role moderatorRole = roleRepository.findByName(RoleType.MODERATOR).orElseThrow();

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailVerified(true)
                .active(true)
                .roles(Set.of(moderatorRole))
                .build();

        userRepository.save(user);

        createLog("CREATE_MODERATOR", "Created moderator " + user.getEmail());

        return toResponse(user);
    }

    public void deleteSetting(Long id) {

        systemSettingRepository.deleteById(id);
    }

    private AdminUserResponse toResponse(User user) {

        return AdminUserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .emailVerified(Boolean.TRUE.equals(user.getEmailVerified()))
                .active(Boolean.TRUE.equals(user.getActive()))
                .roles(user.getRoles() == null ? Set.of() : user.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet()))
                .build();
    }
}