package com.ecole221.planification.data.entities;
import com.ecole221.planification.data.enums.EtatCours;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cours extends AbstractEntity{
    @Column(nullable = false)
    private Integer nombreHeureGlobal;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatCours etatCours =  EtatCours.EN_COURS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professeur_id", nullable = false)
    private Professeur professeur;

    @OneToMany(mappedBy = "cours")
    private List<SessionCours> sessionCours;

    @OneToMany(mappedBy = "cours")
    private List<CoursClasse> coursClasses;






}
