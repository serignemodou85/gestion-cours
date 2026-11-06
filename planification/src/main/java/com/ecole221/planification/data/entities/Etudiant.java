package com.ecole221.planification.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Etudiant extends AbstractEntity {
    @Column(nullable = false,unique = true)
    private String matricule;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;
}
