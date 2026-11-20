package com.ecole221.planification.services;

import com.ecole221.planification.data.entities.Professeur;
import com.ecole221.planification.data.entities.SessionCours;

import java.time.YearMonth;
import java.util.List;

public record BilanMoisProfesseur(Professeur professeur, YearMonth mois,
                                  int totalHeuresPlanifiees, int totalHeuresEffectuees,
                                  List<SessionCours> sessions) {
}
