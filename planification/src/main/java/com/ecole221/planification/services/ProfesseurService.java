package com.ecole221.planification.services;

import com.ecole221.planification.web.dto.response.SessionsMoisProfesseurDto;
import java.time.YearMonth;
public interface ProfesseurService {
    SessionsMoisProfesseurDto sessionsDuMois(Long professeurId, YearMonth mois, Long moduleId);

}
