package com.ecole221.planification.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Module extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String libelle;
    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<Cours> cours;




}
