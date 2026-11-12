package com.ecole221.planification.web.controllers;


import com.ecole221.planification.web.dto.RestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.ecole221.planification.services.ModuleService;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ModuleControllerImpl implements ModuleController {

    private final ModuleService ModuleService;

    @Override
    public ResponseEntity<Map<Object, Object>> lister() {
        return ResponseEntity.ok(RestResponseDto.response(ModuleService.listerActifs(), HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Map<Object, Object>> trouver(Long id) {
        return ResponseEntity.ok(RestResponseDto.response(ModuleService.trouverParId(id), HttpStatus.OK));
    }
}

