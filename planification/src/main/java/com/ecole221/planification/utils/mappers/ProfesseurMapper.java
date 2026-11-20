package com.ecole221.planification.utils.mappers;

import com.ecole221.planification.services.BilanMoisProfesseur;
import com.ecole221.planification.web.dto.response.SessionsMoisProfesseurDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfesseurMapper {

    private final SessionCoursMapper sessionCoursMapper;

    public SessionsMoisProfesseurDto toDto(BilanMoisProfesseur b) {
        return new SessionsMoisProfesseurDto(
                new SessionsMoisProfesseurDto.ProfesseurResume(
                        b.professeur().getId(), b.professeur().getNom(), b.professeur().getPrenom()),
                b.mois().toString(),
                b.totalHeuresPlanifiees(),
                b.totalHeuresEffectuees(),
                b.sessions().stream().map(sessionCoursMapper::toDto).toList());
    }
}
