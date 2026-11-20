package com.ecole221.planification.utils.mappers;
import com.ecole221.planification.data.entities.Module;
import com.ecole221.planification.web.dto.response.ModuleResponseDto;
import org.springframework.stereotype.Component;
@Component
public class ModuleMapper {
    public ModuleResponseDto toDto(Module module){
        return new ModuleResponseDto(module.getId(), module.getLibelle());
    }
}