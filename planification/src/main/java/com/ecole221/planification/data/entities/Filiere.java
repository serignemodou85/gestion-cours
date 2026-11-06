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
public class Filiere extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String libelle;

    @OneToMany(mappedBy ="filiere")
    private List<Classe> classes;
}
