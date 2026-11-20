package com.ecole221.planification.web.dto.response;
import com.ecole221.planification.data.enums.EtatCours;

public record CoursResponseDto(Long id, Integer nombreHeureGlobal, EtatCours etatCours,
                               String moduleLibelle, String professeur, String semestreLibelle) {
}
