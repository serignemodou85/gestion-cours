package com.ecole221.planification.web.dto.response;


import java.util.List;

public record SessionsMoisProfesseurDto(
        ProfesseurResume professeur,
        String mois,
        int totalHeuresPlanifiees,
        int totalHeuresEffectuees,

        List<SessionCoursResponseDto> sessions) {

    public record ProfesseurResume(Long id, String nom, String prenom) {
    }
}
