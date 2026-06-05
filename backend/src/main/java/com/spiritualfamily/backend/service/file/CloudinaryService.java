package com.spiritualfamily.backend.service.file;

import java.io.IOException;
import java.util.Map;

import java.util.Set;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Service
public class CloudinaryService {

    private static final long MAX_UPLOAD_SIZE = 10L * 1024 * 1024;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "application/pdf"
    );

    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloudinary.url}") String cloudinaryUrl) {
        if (cloudinaryUrl == null || cloudinaryUrl.isBlank()) {
            this.cloudinary = null;
        } else {
            this.cloudinary = new Cloudinary(cloudinaryUrl);
        }
    }

    public String upload(
            MultipartFile file
    ) {
        if (cloudinary == null) {
            throw new RuntimeException("Cloudinary is not configured");
        }

        try {
            validateFile(file);
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of("resource_type", "auto"));
            Object secureUrl = uploadResult.get("secure_url");
            return secureUrl != null ? secureUrl.toString() : uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary upload failed", e);
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is required");
        }

        if (file.getSize() > MAX_UPLOAD_SIZE) {
            throw new RuntimeException("File size must be 10 MB or less");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new RuntimeException("Unsupported file type");
        }
    }
}