package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre,Long> {
}
