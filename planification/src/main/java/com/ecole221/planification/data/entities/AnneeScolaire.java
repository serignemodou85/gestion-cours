package com.ecole221.planification.data.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AnneeScolaire extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String libelle;
    @Column(nullable = false)
    private LocalDate dateDebut;
    @Column(nullable = false)
    private LocalDate dateFin;
    @Column(nullable = false)
    private Boolean estCourante=false;
    @JsonIgnore
    @OneToMany(mappedBy = "anneeScolaire")
    private List<Semestre> semestres;
    @JsonIgnore
    @OneToMany(mappedBy = "anneeScolaire")
    private List<ClasseAnneeScolaire> classesAnneeScolaire;


}
