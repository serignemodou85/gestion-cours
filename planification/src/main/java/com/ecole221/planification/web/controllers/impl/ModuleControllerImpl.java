package com.ecole221.planification.web.controllers.impl;

import com.ecole221.planification.services.ModuleService;
import com.ecole221.planification.utils.mappers.ModuleMapper;
import com.ecole221.planification.web.controllers.ModuleController;
import com.ecole221.planification.web.dto.RestResponseDto;
import com.ecole221.planification.web.dto.response.ModuleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ModuleControllerImpl implements ModuleController {

    private final ModuleService moduleService;
    private final ModuleMapper moduleMapper;

    @Override
    public ResponseEntity<Map<Object, Object>> lister() {
        List<ModuleResponseDto> modules = moduleService.listerActifs().stream()
                .map(moduleMapper::toDto)
                .toList();
        return ResponseEntity.ok(RestResponseDto.response(modules, HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Map<Object, Object>> trouver(Long id) {
        return ResponseEntity.ok(RestResponseDto.response(
                moduleMapper.toDto(moduleService.trouverParId(id)), HttpStatus.OK));
    }
}
