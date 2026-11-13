package com.ecole221.planification.web.controllers;

import com.ecole221.planification.web.dto.request.SessionCoursRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api/v1/cours/{coursId}/sessions")
public interface SessionCoursController {

    @PostMapping
    ResponseEntity<Map<Object, Object>> planifier(@PathVariable Long coursId,
                                                  @Valid @RequestBody SessionCoursRequestDto dto);
}
