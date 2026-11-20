package com.ecole221.planification.web.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Map;
@Tag(name = "Modules", description = "Consultation des modules")
@RequestMapping("/api/v1/modules")
public interface ModuleController {
    @Operation(summary = "Lister les modules actifs, page par page")
    @ApiResponse(responseCode = "200", description = "Page de modules")
    @GetMapping
    ResponseEntity<Map<Object, Object>> lister(@PageableDefault(size = 10) Pageable pageable);

    @Operation(summary = "Consulter un module")
    @ApiResponse(responseCode = "200", description = "Le module")
    @ApiResponse(responseCode = "404", description = "Module introuvable")
    @GetMapping("/{id}")
    ResponseEntity<Map<Object, Object>> trouver(@PathVariable Long id);
}
