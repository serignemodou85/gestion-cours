package com.ecole221.planification.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.YearMonth;
import java.util.Map;

@RequestMapping("/api/v1/professeurs")
public interface ProfesseurController {

    @GetMapping("/{professeurId}/sessions")
    ResponseEntity<Map<Object, Object>> sessionsDuMois(
            @PathVariable Long professeurId,
            @RequestParam(required = false) YearMonth mois,
            @RequestParam(required = false) Long moduleId);
}
