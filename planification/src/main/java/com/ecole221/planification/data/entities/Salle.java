package com.ecole221.planification.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

public class Salle extends AbstractEntity {
    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private Integer nombrePlaces ;

    @OneToMany(mappedBy = "salle")
    private List<SessionCours> sessionCours;



}
