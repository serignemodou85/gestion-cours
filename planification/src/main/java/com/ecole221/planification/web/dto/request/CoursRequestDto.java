package com.ecole221.planification.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoursRequestDto {

    @NotNull(message = "Le nombre d'heures global est obligatoire")
    @Positive(message = "Le nombre d'heures global doit etre strictement positif")
    private Integer nombreHeureGlobal;

    @NotNull(message = "Le module est obligatoire")
    private Long moduleId;

    @NotNull(message = "Le professeur est obligatoire")
    private Long professeurId;

    @NotNull(message =  "Le semestre est obligatoire")
    private Long semestreId;

    private List<Long> classeIds;
}
