package com.ecole221.planification.config;

import com.ecole221.planification.data.entities.*;
import com.ecole221.planification.data.entities.Module;
import com.ecole221.planification.data.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final AnneeScolaireRepository anneeScolaireRepository;
    private final SemestreRepository semestreRepository;
    private final FiliereRepository filiereRepository;
    private final NiveauRepository niveauRepository;
    private final ClasseRepository classeRepository;
    private final ProfesseurRepository professeurRepository;
    private final SalleRepository salleRepository;
    private final ModuleRepository moduleRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    public void run(String... args) {
        if (anneeScolaireRepository.count() > 0) {
            log.info("Jeu de donnees deja present : aucune insertion.");
            return;
        }


        // 1. Annee scolaire courante
        AnneeScolaire anneeScolaire = new AnneeScolaire();
        anneeScolaire.setLibelle("2026-2027");
        anneeScolaire.setDateDebut(LocalDate.of(2026, 9, 7));
        anneeScolaire.setDateFin(LocalDate.of(2027, 9, 7));
        anneeScolaire.setEstCourante(Boolean.TRUE);
        anneeScolaireRepository.save(anneeScolaire);

        // 2. Semestre
        Semestre semestre1 = new Semestre();
        semestre1.setLibelle("Semestre 1");
        semestre1.setDateDebut(LocalDate.of(2026, 9, 7));
        semestre1.setDateFin(LocalDate.of(2027, 1, 30));
        semestre1.setAnneeScolaire(anneeScolaire);
        semestreRepository.save(semestre1);

        // 3. Filieres
        Filiere gl = new Filiere();
        gl.setLibelle("Genie Logiciel");
        filiereRepository.save(gl);

        Filiere rsx= new Filiere();
        rsx.setLibelle("Reseaux");
        filiereRepository.save(rsx);

        Filiere dataIa = new Filiere();
        dataIa.setLibelle("Data IA");
        filiereRepository.save(dataIa);


        // 4. Niveaux
        Niveau licence1 = new Niveau();
        licence1.setLibelle("Licence 1");
        niveauRepository.save(licence1);
        Niveau licence2 = new Niveau();
        licence2.setLibelle("Licence 2");
        niveauRepository.save(licence2);
        Niveau licence3 = new Niveau();
        licence3.setLibelle("Licence 3");
        niveauRepository.save(licence3);

        Niveau master1 = new Niveau();
        master1.setLibelle("Master 1");
        niveauRepository.save(master1);
        Niveau master2 = new Niveau();
        master2.setLibelle("Master 2");
        niveauRepository.save(master2);

        // 5. Classes
        Classe l1 = new Classe();
        l1.setLibelle("L1GenieLogiciel");
        l1.setFiliere(gl);
        l1.setNiveau(licence1);
        classeRepository.save(l1);
        Classe l2 = new Classe();
        l2.setLibelle("L2GenieLogiciel");
        l2.setFiliere(gl);
        l2.setNiveau(licence2);
        classeRepository.save(l2);
        Classe l3 = new Classe();
        l3.setLibelle("L3GenieLogiciel");
        l3.setFiliere(gl);
        l3.setNiveau(licence3);
        classeRepository.save(l3);
        // master
        Classe m1= new Classe();
        m1.setLibelle("M1Data et IA");
        m1.setFiliere(dataIa);
        m1.setNiveau(master1);
        classeRepository.save(m1);
        Classe m2= new Classe();
        m2.setLibelle("M2Reseaux");
        m2.setFiliere(rsx);
        m2.setNiveau(master2);
        classeRepository.save(m2);



        // 6. Professeurs
        Professeur marega = new Professeur();
        marega.setNom("marega");
        marega.setPrenom("Aboubacar");
        marega.setSpecialite("Devops");
        marega.setGrade("Ingenieur");
        professeurRepository.save(marega);

        Professeur fall = new Professeur();
        fall.setNom("Fall");
        fall.setPrenom("Modou");
        fall.setSpecialite("Data et IA");
        fall.setGrade("Ingenieur");
        professeurRepository.save(fall);
        // 7. Salles
        Salle salle1 = new Salle();
        salle1.setNom("Salle 1");
        salle1.setNumero("A101");
        salle1.setNombrePlaces(60);

        salleRepository.save(salle1);
        Salle salle2 = new Salle();
        salle2.setNom("Salle 2");
        salle2.setNumero("B202");
        salle2.setNombrePlaces(60);
        salleRepository.save(salle2);

        // 8. Modules
        Module angular = new Module();
        angular.setLibelle("Angular");
        moduleRepository.save(angular);

        Module springBoot = new Module();
        springBoot.setLibelle("Spring Boot");
        moduleRepository.save(springBoot);

        Module algebreLineaire = new Module();
        algebreLineaire.setLibelle("Algebre Lineaire");
        moduleRepository.save(algebreLineaire);

        // 9. Etudiants
        Etudiant balla = new Etudiant();
        balla.setMatricule("A123");
        balla.setNom("Beye");
        balla.setPrenom("Balla");
        balla.setEmail("balla@gmail.com");
        balla.setClasse(l2);
        etudiantRepository.save(balla);

        Etudiant khadija = new Etudiant();
        khadija.setMatricule("A124");
        khadija.setNom("Aidara");
        khadija.setPrenom("Khadija");
        khadija.setEmail("khadija@gmail.com");
        khadija.setClasse(m2);
        etudiantRepository.save(khadija);

        Etudiant rokhaya = new Etudiant();
        rokhaya.setMatricule("A125");
        rokhaya.setNom("Seck");
        rokhaya.setPrenom("Rokhaya");
        rokhaya.setEmail("rokhaya@gmail.com");
        rokhaya.setClasse(m1);
        etudiantRepository.save(rokhaya);

        log.info("Jeu de donnees insere.");
    }
}
