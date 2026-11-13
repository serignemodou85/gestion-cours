package com.ecole221.planification.services;

import com.ecole221.planification.data.entities.Cours;
import com.ecole221.planification.web.dto.request.CoursRequestDto;

public interface CoursService {
    Cours creer(CoursRequestDto dto);
}
