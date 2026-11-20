package com.ecole221.planification.utils.mappers;

import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.web.dto.response.SessionCoursResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SessionCoursMapper {
    public SessionCoursResponseDto toDto(SessionCours s) {
        return new SessionCoursResponseDto(
                s.getId(), s.getDate(), s.getHeureDebut(), s.getHeureFin(),
                s.getNombreHeure(), s.getTypeSession(), s.getEtatSession(),
                s.getSalle() != null ? s.getSalle().getNumero() : null,
                s.getLienVisio(),
                s.getCours().getId(),
                s.getCours().getModule().getLibelle()
        );
    }
}