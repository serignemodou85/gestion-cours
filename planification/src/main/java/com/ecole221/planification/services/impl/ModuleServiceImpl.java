package com.ecole221.planification.services.impl;
import com.ecole221.planification.data.repository.ModuleRepository;
import com.ecole221.planification.services.ModuleService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.data.entities.Module;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)//chaque méthode s'exécute dans une transaction en lecture seule
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public List<Module> listerActifs() {
        return moduleRepository.findByIsActiveTrue();
    }

    @Override
    public Module trouverParId(Long id) {
        return moduleRepository.findById(id) //une boîte (Optional) contenant le module
                .orElseThrow(() -> new EntityNotFoundException("Module", id));
    }
}
