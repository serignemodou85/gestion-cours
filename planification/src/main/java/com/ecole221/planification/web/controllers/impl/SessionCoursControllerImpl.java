package com.ecole221.planification.web.controllers.impl;
import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.services.SessionCoursService;
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
    @Override
    public ResponseEntity<Map<Object, Object>> planifier(Long coursId, SessionCoursRequestDto dto) {
        SessionCours session = sessionCoursService.planifier(coursId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestResponseDto.response(session, HttpStatus.CREATED));
    }

}
