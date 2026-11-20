package com.ecole221.planification.web.dto.response;

import com.ecole221.planification.data.enums.EtatSession;
import com.ecole221.planification.data.enums.TypeSession;

import java.time.LocalDate;
import java.time.LocalTime;

public record SessionCoursResponseDto(Long id, LocalDate date, LocalTime heureDebut, LocalTime heureFin,
                                      Integer nombreHeure, TypeSession typeSession, EtatSession etatSession,
                                      String salle, String lienVisio, Long coursId , String moduleLibelle) {
}
