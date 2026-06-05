package com.spiritualfamily.backend.service.file;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spiritualfamily.backend.exception.BadRequestException;

@Service
public class FileValidationService {

    private static final long MAX_UPLOAD_SIZE = 10L * 1024 * 1024;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "application/pdf"
    );

    public void validate(MultipartFile file) {

        if (file == null) {
            throw new BadRequestException("File is required");
        }

        if (file.isEmpty()) {
            throw new BadRequestException("File is required");
        }

        if (file.getSize() > MAX_UPLOAD_SIZE) {
            throw new BadRequestException("File size must be 10 MB or less");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new BadRequestException("Unsupported file type");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new BadRequestException("Original filename is required");
        }
    }
}
