package com.ecole221.planification.services;

import java.util.List;
import com.ecole221.planification.data.entities.Module;

public interface ModuleService {
    List<Module> listerActifs();

    Module trouverParId(Long id);


}
