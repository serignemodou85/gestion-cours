package com.ecole221.planification.data.entities;

import com.ecole221.planification.data.enums.EtatSession;
import com.ecole221.planification.data.enums.TypeSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SessionCours extends AbstractEntity{

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime heureDebut;

    @Column(nullable = false)
    private LocalTime heureFin;

    @Column(nullable = false)
    private Integer nombreHeure;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSession typeSession;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatSession etatSession = EtatSession.PLANIFIEE;
    @Column(nullable = true)
    private String lienVisio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_id", nullable = true)
    private Salle salle;



}
