package com.ecole221.planification.data.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cours_id", "classe_id"}))
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CoursClasse extends AbstractEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;



}
