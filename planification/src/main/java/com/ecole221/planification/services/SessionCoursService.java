package com.ecole221.planification.services;


import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.web.dto.request.SessionCoursRequestDto;
public interface SessionCoursService {
    SessionCours planifier(Long coursId , SessionCoursRequestDto sessionCoursRequestDto);

}
