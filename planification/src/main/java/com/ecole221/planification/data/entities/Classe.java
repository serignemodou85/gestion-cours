package com.ecole221.planification.data.entities;
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
public class Classe extends AbstractEntity {
    @Column(nullable = false,unique = true)
    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_id", nullable = false)
    private Niveau niveau;
    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;
    @OneToMany(mappedBy = "classe")
    private List<ClasseAnneeScolaire> classesAnneeScolaire;
    @OneToMany(mappedBy = "classe")
    private List<CoursClasse> coursClasses;




}
