package com.ecole221.planification.web.controllers.impl;
import com.ecole221.planification.data.entities.Cours;
import com.ecole221.planification.services.CoursService;
import com.ecole221.planification.web.controllers.CoursController;
import com.ecole221.planification.web.dto.RestResponseDto;
import com.ecole221.planification.web.dto.request.CoursRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CoursControllerImpl implements CoursController {
    private final CoursService coursService;
    @Override
    public ResponseEntity<Map<Object, Object>> creer(CoursRequestDto dto) {
        Cours cours = coursService.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestResponseDto.response(cours, HttpStatus.CREATED));
    }
}