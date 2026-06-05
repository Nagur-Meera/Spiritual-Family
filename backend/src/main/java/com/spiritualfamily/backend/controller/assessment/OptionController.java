package com.spiritualfamily.backend.controller.assessment;

import com.spiritualfamily.backend.dto.assessment.*;
import com.spiritualfamily.backend.service.assessment.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService service;

    @PostMapping
    public OptionResponse create(
            @RequestBody OptionRequest request
    ) {

        return service.create(request);
    }

    @GetMapping
    public List<OptionResponse> getAll() {

        return service.getAll();
    }
}