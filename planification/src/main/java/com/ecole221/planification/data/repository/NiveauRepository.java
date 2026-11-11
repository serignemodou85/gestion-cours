package com.ecole221.planification.data.repository;


import com.ecole221.planification.data.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiveauRepository extends JpaRepository<Niveau,Long> {
}
