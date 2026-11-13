package com.ecole221.planification.web.dto.response;

import com.ecole221.planification.data.entities.SessionCours;

import java.util.List;

public record SessionsMoisProfesseurDto(
        ProfesseurResume professeur,
        String mois,
        int totalHeuresPlanifiees,
        int totalHeuresEffectuees,
        List<SessionCours> sessions) {

    public record ProfesseurResume(Long id, String nom, String prenom) {
    }
}
