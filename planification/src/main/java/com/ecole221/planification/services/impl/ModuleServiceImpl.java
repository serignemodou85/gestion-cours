package com.ecole221.planification.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ecole221.planification.exceptions.EntityNotFoundException;
import com.ecole221.planification.data.repository.ModuleRepository;
import com.ecole221.planification.data.entities.Module;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService {

    private final ___ ModuleRepository;

    @Override
    public List<Module> listerActifs() {
        return ModuleRepository.___();
    }

    @Override
    public Module trouverParId(Long id) {
        return ModuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("___", id));
    }
}
