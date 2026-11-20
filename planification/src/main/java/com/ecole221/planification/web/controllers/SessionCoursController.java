package com.ecole221.planification.web.controllers;

import com.ecole221.planification.web.dto.request.SessionCoursRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.Map;
@Tag(name = "Sessions", description = "Planification des sessions de cours")
@RequestMapping("/api/v1/cours/{coursId}/sessions")
public interface SessionCoursController {
    @Operation(summary = "Planifier une session d'un cours, en présentiel ou en ligne")
    @ApiResponse(responseCode = "201", description = "Session créée, nombre d'heures calculé")
    @ApiResponse(responseCode = "400", description = "Horaires incohérents, salle ou lien visio incorrect , ou date hors du semestre")
    @ApiResponse(responseCode = "404", description = "Cours ou salle introuvable")
    @ApiResponse(responseCode = "409", description = "Quota d'heures dépassé (RG5), salle ou professeur déjà occupé ")

    @PostMapping
    ResponseEntity<Map<Object, Object>> planifier(@PathVariable Long coursId,
                                                  @Valid @RequestBody SessionCoursRequestDto dto);
}
