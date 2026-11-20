package com.ecole221.planification.web.controllers.impl;
import com.ecole221.planification.services.SessionCoursService;
import com.ecole221.planification.utils.mappers.SessionCoursMapper;
import com.ecole221.planification.web.controllers.SessionCoursController;
import com.ecole221.planification.web.dto.RestResponseDto;
import com.ecole221.planification.web.dto.request.SessionCoursRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class SessionCoursControllerImpl implements SessionCoursController {
    private final SessionCoursService sessionCoursService;
    private final SessionCoursMapper sessionCoursMapper;

    @Override
    public ResponseEntity<Map<Object, Object>> planifier(Long coursId, SessionCoursRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestResponseDto.response(sessionCoursMapper.toDto(sessionCoursService.planifier(coursId, dto)), HttpStatus.CREATED))
                ;
    }
}
