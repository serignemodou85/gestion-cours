package com.ecole221.planification.services.impl;
import com.ecole221.planification.data.repository.ModuleRepository;
import com.ecole221.planification.services.ModuleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.data.entities.Module;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)//chaque méthode s'exécute dans une transaction en lecture seule
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public Page<Module> listerActifs(Pageable pageable){
        return moduleRepository.findByIsActiveTrue(pageable);

    }

    @Override
    public Module trouverParId(Long id) {
        return moduleRepository.findById(id) //une boîte (Optional) contenant le module
                .orElseThrow(() -> new EntityNotFoundException("Module", id));
    }
}
