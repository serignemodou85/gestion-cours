package com.ecole221.planification.services;

import com.ecole221.planification.data.entities.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ModuleService {
    Page<Module> listerActifs(Pageable pageable);


    Module trouverParId(Long id);


}
