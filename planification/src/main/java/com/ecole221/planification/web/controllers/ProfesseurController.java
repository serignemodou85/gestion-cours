package com.ecole221.planification.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.YearMonth;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Professeurs", description = "Suivi des sessions et des heures des professeurs")
@RequestMapping("/api/v1/professeurs")
public interface ProfesseurController {

    @Operation(summary = "Sessions et heures d'un professeur sur un mois, filtre module facultatif")
    @ApiResponse(responseCode = "200", description = "Sessions du mois, heures planifiées et effectuées")
    @ApiResponse(responseCode = "404", description = "Professeur introuvable")

    @GetMapping("/{professeurId}/sessions")
    ResponseEntity<Map<Object, Object>> sessionsDuMois(
            @PathVariable Long professeurId,
            @RequestParam(required = false) YearMonth mois,
            @RequestParam(required = false) Long moduleId);
}
