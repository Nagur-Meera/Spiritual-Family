package com.spiritualfamily.backend.controller.assessment;

import com.spiritualfamily.backend.dto.assessment.*;
import com.spiritualfamily.backend.service.assessment.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @PostMapping
    public ResponseEntity<QuestionResponse>
    create(
            @RequestBody QuestionRequest request
    ) {

        return ResponseEntity.ok(
                service.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>>
    getAll() {

        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse>
    getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    delete(
            @PathVariable Long id
    ) {

        service.delete(id);

        return ResponseEntity.ok(
                "Question Deleted"
        );
    }
}