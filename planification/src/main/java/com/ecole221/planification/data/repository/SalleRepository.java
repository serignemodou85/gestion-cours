package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
