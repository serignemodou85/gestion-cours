package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {
}
