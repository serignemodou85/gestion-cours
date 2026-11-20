package com.ecole221.planification.web.controllers;

import com.ecole221.planification.web.dto.request.CoursRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.Map;
@Tag(name = "Cours", description = "Planification des cours")
@RequestMapping("/api/v1/cours")
public interface CoursController {
    @Operation(summary = "Planifier un cours pour une ou plusieurs classes")
    @ApiResponse(responseCode = "201", description = "Cours créé")
    @ApiResponse(responseCode = "400", description = "Donnees invalides, aucune classe, ou semestre hors de l'année courante")
    @ApiResponse(responseCode = "404", description = "Module, professeur, semestre ou classe introuvable")
    @PostMapping
    ResponseEntity<Map<Object, Object>> creer(@Valid @RequestBody CoursRequestDto dto);
}
