package com.ecole221.planification.data.repository;
import com.ecole221.planification.data.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours,Long>{

}
