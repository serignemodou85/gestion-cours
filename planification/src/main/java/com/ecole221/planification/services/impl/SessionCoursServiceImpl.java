package com.ecole221.planification.services.impl;
import com.ecole221.planification.data.entities.Cours;
import com.ecole221.planification.data.entities.Salle;
import com.ecole221.planification.data.entities.Semestre;
import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.data.enums.EtatSession;
import com.ecole221.planification.data.enums.TypeSession;
import com.ecole221.planification.data.repository.CoursRepository;
import com.ecole221.planification.data.repository.SalleRepository;
import com.ecole221.planification.data.repository.SessionCoursRepository;
import com.ecole221.planification.exceptions.BadRequestException;
import com.ecole221.planification.exceptions.ConflictException;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.services.SessionCoursService;
import com.ecole221.planification.web.dto.request.SessionCoursRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j


public class SessionCoursServiceImpl implements SessionCoursService {
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final CoursRepository coursRepository;
    private final SalleRepository salleRepository;
    private final SessionCoursRepository sessionCoursRepository;
    @Transactional
    public SessionCours planifier(Long coursId, SessionCoursRequestDto dto) {

        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new EntityNotFoundException("Cours", coursId));

        // 2. Les heures sont cohérentes (400)
        if (!dto.getHeureFin().isAfter(dto.getHeureDebut())) {
            throw new BadRequestException("L'heure de fin doit être postérieure à l'heure de début .");
        }

        // 3. RG3 : salle ou lien visio selon le type (400)
        Salle salle = null;
        if (dto.getTypeSession() == TypeSession.PRESENTIEL) {
            if (dto.getSalleId() == null) {
                throw new BadRequestException("Une session en présentiel doit se tenir dans une salle .");
            }
            salle = salleRepository.findById(dto.getSalleId())
                    .orElseThrow(() -> new EntityNotFoundException("Salle", dto.getSalleId()));
        } else {
            if (dto.getSalleId() != null) {
            throw new BadRequestException("Une session en ligne n'occupe pas de salle");
            }
        if (dto.getLienVisio() == null || dto.getLienVisio().isBlank()) {
            throw new BadRequestException("Le lien de visioconférence est obligatoire pour une session en ligne");
            }
        }

        // date hors des bornes du semestre
        Semestre semestre = cours.getSemestre();
        if (dto.getDate().isBefore(semestre.getDateDebut()) || dto.getDate().isAfter(semestre.getDateFin())) {
            throw new BadRequestException("La date du " + dto.getDate().format(FORMAT_DATE)
                    + " est hors du " + semestre.getLibelle());
        }
        

        // 5. Calcul du nombre d'heures
        int nombreHeure = (int) Duration.between(dto.getHeureDebut(), dto.getHeureFin()).toHours();

        // 6. RG5 : le quota n'est pas dépasse (409)
        long dejaPlanifiees = sessionCoursRepository.totalHeuresPlanifiees(coursId ,EtatSession.ANNULEE);
        if (dejaPlanifiees + nombreHeure > cours.getNombreHeureGlobal()){
            throw new ConflictException("Le quota de " + cours.getNombreHeureGlobal()
                    + " heures serait dépassé : " + dejaPlanifiees + " heures déjà planifiées, "
                    + nombreHeure + " demandées");
        }
        // 7. RG4 : la salle est libre (409)
        if(salle != null){
            List<SessionCours> conflits = sessionCoursRepository.conflitsSalle(salle.getId(), dto.getDate(),
                    dto.getHeureDebut(), dto.getHeureFin(), EtatSession.ANNULEE);

            if (!conflits.isEmpty()){
                SessionCours existante = conflits.get(0);
                throw new ConflictException("La salle " + salle.getNumero() + " est déjà occupée le "
                        + existante.getDate().format(FORMAT_DATE) + " de " + existante.getHeureDebut()
                        + " à " + existante.getHeureFin());
            }
        }
        // 8. RG4 : le professeur est libre (409)
        List<SessionCours> conflitsProf = sessionCoursRepository.conflitsProfesseur(
                cours.getProfesseur().getId(), dto.getDate(), dto.getHeureDebut(), dto.getHeureFin(),
                EtatSession.ANNULEE);
        if (!conflitsProf.isEmpty()){
            SessionCours existante = conflitsProf.get(0);
            throw new ConflictException("Le professeur " + cours.getProfesseur().getPrenom() + " "
                    + cours.getProfesseur().getNom() + " a déjà une session le "
                    + existante.getDate().format(FORMAT_DATE) + " de " + existante.getHeureDebut()+ " à "
                    +existante.getHeureFin());
        }

        SessionCours session = new SessionCours();
        session.setCours(cours);
        session.setSalle(salle);
        session.setDate(dto.getDate());
        session.setHeureDebut(dto.getHeureDebut());
        session.setHeureFin(dto.getHeureFin());
        session.setNombreHeure(nombreHeure);
        session.setTypeSession(dto.getTypeSession());
        session.setLienVisio(dto.getLienVisio());

        return sessionCoursRepository.save(session);


    }
}
