package com.ecole221.planification.services.impl;

import com.ecole221.planification.data.entities.Professeur;
import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.data.enums.EtatSession;
import com.ecole221.planification.data.repository.ProfesseurRepository;
import com.ecole221.planification.data.repository.SessionCoursRepository;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.services.ProfesseurService;
import com.ecole221.planification.web.dto.response.SessionsMoisProfesseurDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfesseurServiceImpl implements ProfesseurService {

    private final ProfesseurRepository professeurRepository;
    private final SessionCoursRepository sessionCoursRepository;

    @Override
    public SessionsMoisProfesseurDto sessionsDuMois(Long professeurId, YearMonth mois, Long moduleId) {
        Professeur professeur = professeurRepository.findById(professeurId)
                .orElseThrow(() -> new EntityNotFoundException("Professeur", professeurId));

        // Mois en cours par défaut
        YearMonth periode = (mois != null) ? mois : YearMonth.now();

        // Filtrage par mois et par module : en base
        List<SessionCours> sessions = sessionCoursRepository.sessionsDuProfesseur(
                professeurId, periode.atDay(1), periode.atEndOfMonth(), moduleId);

        // RG8 : seules les sessions VALIDEE comptent comme effectuées
        int effectuees = sessions.stream()
                .filter(s -> s.getEtatSession() == EtatSession.VALIDEE)
                .mapToInt(SessionCours::getNombreHeure)
                .sum();

        // Planifiées : PLANIFIEE et VALIDEE, jamais ANNULÉE
        int planifiees = sessions.stream()
                .filter(s -> s.getEtatSession() == EtatSession.PLANIFIEE
                        || s.getEtatSession() == EtatSession.VALIDEE)
                .mapToInt(SessionCours::getNombreHeure)
                .sum();

        return new SessionsMoisProfesseurDto(
                new SessionsMoisProfesseurDto.ProfesseurResume(
                        professeur.getId(), professeur.getNom(), professeur.getPrenom()),
                periode.toString(),
                planifiees,
                effectuees,
                sessions);
    }
}
