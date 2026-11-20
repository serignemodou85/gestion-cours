package com.ecole221.planification.utils.mappers;
import com.ecole221.planification.data.entities.Cours;
import com.ecole221.planification.web.dto.response.CoursResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CoursMapper {
    public CoursResponseDto toDto(Cours cours) {
        return new CoursResponseDto(
                cours.getId(),
                cours.getNombreHeureGlobal(),
                cours.getEtatCours(),
                cours.getModule().getLibelle(),
                cours.getProfesseur().getPrenom() + " " + cours.getProfesseur().getNom(),
                cours.getSemestre().getLibelle()
        );
    }
}
