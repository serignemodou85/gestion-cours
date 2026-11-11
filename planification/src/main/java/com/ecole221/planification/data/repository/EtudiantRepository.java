package com.ecole221.planification.data.repository;
import com.ecole221.planification.data.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{

    
}
