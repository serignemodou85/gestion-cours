package com.ecole221.planification.web.controllers;

import com.ecole221.planification.web.dto.request.CoursRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api/v1/cours")
public interface CoursController {

    @PostMapping
    ResponseEntity<Map<Object, Object>> creer(@Valid @RequestBody CoursRequestDto dto);
}
