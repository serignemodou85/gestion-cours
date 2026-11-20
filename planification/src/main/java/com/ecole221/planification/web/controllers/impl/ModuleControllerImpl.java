package com.ecole221.planification.web.controllers.impl;

import com.ecole221.planification.services.ModuleService;
import com.ecole221.planification.utils.mappers.ModuleMapper;
import com.ecole221.planification.web.controllers.ModuleController;
import com.ecole221.planification.web.dto.RestResponseDto;
import com.ecole221.planification.web.dto.response.ModuleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ModuleControllerImpl implements ModuleController {

    private final ModuleService moduleService;
    private final ModuleMapper moduleMapper;

    @Override
    public ResponseEntity<Map<Object, Object>> lister(Pageable pageable) {
        Page<ModuleResponseDto> page = moduleService.listerActifs(pageable).map(moduleMapper::toDto);
        return ResponseEntity.ok(RestResponseDto.paginateResponse(page, HttpStatus.OK, "modules"));
    }

    @Override
    public ResponseEntity<Map<Object, Object>> trouver(Long id) {
        return ResponseEntity.ok(RestResponseDto.response(
                moduleMapper.toDto(moduleService.trouverParId(id)), HttpStatus.OK));
    }
}
