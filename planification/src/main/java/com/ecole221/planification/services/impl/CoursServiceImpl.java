package com.ecole221.planification.services.impl;
import com.ecole221.planification.data.entities.*;
import com.ecole221.planification.data.entities.Module;
import com.ecole221.planification.data.repository.*;
import com.ecole221.planification.exceptions.BadRequestException;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.services.CoursService;
import com.ecole221.planification.web.dto.request.CoursRequestDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;
    private final ModuleRepository moduleRepository;
    private final ProfesseurRepository professeurRepository;
    private final SemestreRepository semestreRepository;
    private final ClasseRepository classeRepository;
    private final CoursClasseRepository coursClasseRepository;

    @Override
    @Transactional
    public Cours creer(CoursRequestDto dto) {
        // 1. Charger le module, le professeur et le semestre (404 si inconnu)
        Module module = moduleRepository.findById(dto.getModuleId())
                .orElseThrow(() -> new EntityNotFoundException("Module", dto.getModuleId()));
        Professeur professeur = professeurRepository.findById(dto.getProfesseurId())
                .orElseThrow(() -> new EntityNotFoundException("Professeur", dto.getProfesseurId()));
        Semestre semestre = semestreRepository.findById(dto.getSemestreId())
                .orElseThrow(() -> new EntityNotFoundException("Semestre", dto.getSemestreId()));


        // 2. Au moins une classe (400)
        if (dto.getClasseIds() == null || dto.getClasseIds().isEmpty()) {
            throw new BadRequestException("Un cours doit s'adresser a au moins une classe");
        }

        // 3. Le semestre doit appartenir à l'année courante (400)
        if (!semestre.getAnneeScolaire().getEstCourante()) {
            throw new BadRequestException("Le semestre " + dto.getSemestreId()+ " n'appartient pas a l'annee scolaire courante");
        }

        // 4. Créer et enregistrer le cours
        Cours cours = new Cours();
        cours.setNombreHeureGlobal(dto.getNombreHeureGlobal());
        cours.setModule(module);
        cours.setProfesseur(professeur);
        cours.setSemestre(semestre);
        coursRepository.save(cours);

        // 5. Rattacher chaque classe (404 si une classe est inconnue)
        for (Long classeId : dto.getClasseIds()) {
            Classe classe = classeRepository.findById(classeId)
                    .orElseThrow(() -> new EntityNotFoundException("Classe", classeId));

            CoursClasse coursClasse = new CoursClasse();
            coursClasse.setCours(cours);
            coursClasse.setClasse(classe);
            coursClasseRepository.save(coursClasse);
        }

        return cours;
    }


}
