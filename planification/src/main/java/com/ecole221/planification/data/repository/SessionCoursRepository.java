package com.ecole221.planification.data.repository;


import com.ecole221.planification.data.entities.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionCoursRepository extends JpaRepository<SessionCours,Long> {

}
