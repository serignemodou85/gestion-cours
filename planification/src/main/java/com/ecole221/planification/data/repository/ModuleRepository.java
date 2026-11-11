package com.ecole221.planification.data.repository;

import com.ecole221.planification.data.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module,Long> {


}
