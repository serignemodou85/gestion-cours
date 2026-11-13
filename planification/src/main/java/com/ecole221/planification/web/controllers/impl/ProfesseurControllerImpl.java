package com.ecole221.planification.web.controllers.impl;

import com.ecole221.planification.services.ProfesseurService;
import com.ecole221.planification.web.controllers.ProfesseurController;
import com.ecole221.planification.web.dto.RestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class ProfesseurControllerImpl implements ProfesseurController {

    private final ProfesseurService professeurService;

    @Override
    public ResponseEntity<Map<Object, Object>> sessionsDuMois(Long professeurId, YearMonth mois, Long moduleId) {
        return ResponseEntity.ok(RestResponseDto.response(professeurService.sessionsDuMois(professeurId, mois, moduleId), HttpStatus.OK));
    }
}
