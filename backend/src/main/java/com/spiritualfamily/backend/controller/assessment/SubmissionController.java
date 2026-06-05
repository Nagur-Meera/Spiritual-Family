package com.spiritualfamily.backend.controller.assessment;

import com.spiritualfamily.backend.dto.assessment.*;
import com.spiritualfamily.backend.service.assessment.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService service;

    @PostMapping
    public SubmissionResponse submit(
            @RequestBody SubmissionRequest request
    ) {

        return service.submit(request);
    }
}