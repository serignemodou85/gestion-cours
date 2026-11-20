package com.ecole221.planification.services;

import java.time.YearMonth;
public interface ProfesseurService {
    BilanMoisProfesseur sessionsDuMois(Long professeurId, YearMonth mois, Long moduleId);

}
