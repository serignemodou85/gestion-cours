package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.SessionCours;
import com.ecole221.planification.data.enums.EtatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionCoursRepository extends JpaRepository<SessionCours, Long> {

    // RG5 : total des heures deja planifiees pour un cours
    @Query("""
            select coalesce(sum(s.nombreHeure), 0) from SessionCours s
            where s.cours.id = :coursId
              and s.isActive = true
              and s.etatSession <> :annulee
            """)
    Long totalHeuresPlanifiees(@Param("coursId") Long coursId,
                               @Param("annulee") EtatSession annulee);

    // RG4 : sessions de cette salle qui chevauchent le creneau
    @Query("""
            select s from SessionCours s
            where s.salle.id = :salleId
              and s.date = :date
              and s.heureDebut < :heureFin
              and :heureDebut < s.heureFin
              and s.isActive = true
              and s.etatSession <> :annulee
            """)
    List<SessionCours> conflitsSalle(@Param("salleId") Long salleId,
                                     @Param("date") LocalDate date,
                                     @Param("heureDebut") LocalTime heureDebut,
                                     @Param("heureFin") LocalTime heureFin,
                                     @Param("annulee") EtatSession annulee);

    // RG4 : sessions du professeur qui chevauchent le creneau
    @Query("""
            select s from SessionCours s
            where s.cours.professeur.id = :professeurId
              and s.date = :date
              and s.heureDebut < :heureFin
              and :heureDebut < s.heureFin
              and s.isActive = true
              and s.etatSession <> :annulee
            """)
    List<SessionCours> conflitsProfesseur(@Param("professeurId") Long professeurId,
                                          @Param("date") LocalDate date,
                                          @Param("heureDebut") LocalTime heureDebut,
                                          @Param("heureFin") LocalTime heureFin,
                                          @Param("annulee") EtatSession annulee);

    @Query("""
            select s from SessionCours s
            where s.cours.professeur.id = :professeurId
            and s.date between :debut and :fin
            and (:moduleId is null or s.cours.module.id = :moduleId) 
            and s.isActive = true
            order by s.date, s.heureDebut
            """)
    List<SessionCours> sessionsDuProfesseur(@Param("professeurId") Long professeurId,
                                            @Param("debut") LocalDate debut,
                                            @Param("fin") LocalDate fin,
                                            @Param("moduleId") Long moduleId);

}
