package com.ecole221.planification.web.dto.request;

import com.ecole221.planification.data.enums.TypeSession;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SessionCoursRequestDto {

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'heure de debut est obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime heureFin;

    @NotNull(message = "Le type de session est obligatoire")
    private TypeSession typeSession;

    private Long salleId;

    private String lienVisio; 
}
