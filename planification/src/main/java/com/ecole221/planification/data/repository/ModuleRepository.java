package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ModuleRepository extends JpaRepository<Module,Long> {

    Page<Module> findByIsActiveTrue(Pageable pageable);


}
