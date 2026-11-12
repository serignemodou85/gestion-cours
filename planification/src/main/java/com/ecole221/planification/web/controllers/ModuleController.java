package com.ecole221.planification.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api/v1/modules")
public interface ModuleController {

    @GetMapping
    ResponseEntity<Map<Object, Object>> lister();

    @GetMapping("/{id}")
    ResponseEntity<Map<Object, Object>> trouver(@PathVariable Long id);
}
