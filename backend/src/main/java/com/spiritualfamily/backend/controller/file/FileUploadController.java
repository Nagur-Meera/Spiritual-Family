package com.spiritualfamily.backend.controller.file;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spiritualfamily.backend.dto.file.FileUploadResponse;
import com.spiritualfamily.backend.service.file.CloudinaryService;
import com.spiritualfamily.backend.service.file.FileStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;

    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public FileUploadResponse upload(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.uploadFile(file);

        return FileUploadResponse.builder()
                .fileName(fileName)
                .message("File Uploaded")
                .build();
    }

    @PostMapping("/cloudinary")
    @PreAuthorize("isAuthenticated()")
    public FileUploadResponse uploadToCloudinary(@RequestParam("file") MultipartFile file) {

        String fileUrl = cloudinaryService.upload(file);

        return FileUploadResponse.builder()
                .fileName(fileUrl)
                .message("File uploaded to Cloudinary")
                .build();
    }

    @GetMapping("/{fileName}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}