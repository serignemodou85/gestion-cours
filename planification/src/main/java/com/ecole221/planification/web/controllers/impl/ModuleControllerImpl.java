package com.ecole221.planification.web.controllers.impl;

import com.ecole221.planification.services.ModuleService;
import com.ecole221.planification.web.controllers.ModuleController;
import com.ecole221.planification.web.dto.RestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ModuleControllerImpl implements ModuleController {

    private final ModuleService moduleService;

    @Override
    public ResponseEntity<Map<Object, Object>> lister() {
        return ResponseEntity.ok(RestResponseDto.response(moduleService.listerActifs(), HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Map<Object, Object>> trouver(Long id) {
        return ResponseEntity.ok(RestResponseDto.response(moduleService.trouverParId(id), HttpStatus.OK));
    }
}
