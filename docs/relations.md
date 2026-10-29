### 7. Les relations, une par une

| # | Entité A | Entité B | Cardinalité | Relation | Où va la clé, et sous quelle forme |
|---|---|---|---|---|---|
| 1 | Professeur | Cours | `1 – 0..*` | OneToMany / ManyToOne | la clé du professeur migre vers `Cours` : `Cours` porte `-professeur : Professeur` (`@ManyToOne` + `@JoinColumn(name = "professeur_id")`), `Professeur` porte `-List<Cours> cours` (`@OneToMany(mappedBy = "professeur")`) |
| 2 | Salle | SessionCours | `0..1 – 0..*` | OneToMany / ManyToOne | la clé de la salle migre vers `SessionCours` : `@ManyToOne` + `@JoinColumn(name = "salle_id")`, colonne **nullable** car une session en ligne n'a pas de salle |
| 3 | Module | Cours | `1 – 0..*` | oneToMany/manyToOne| la cle du module migre vers `Cours`  porte `-module: Module` (`@ManyToOne` + `@JoinColumn(name="module_id"`et dans module on aura `module` porte `List<Cours> cours` (`@OneToMany(mappedBy="module")`) |
| 4 | Semestre | Cours | `1 – 0..*` | oneToMany/ManyToOne  |la cle du semestre migre vers `Cours` porte `semestre:Semestre`(`@ManytoOne` + `@JoinColumn(name="semestre_id"`et dans semestre on aura `semestre` porte `List<Cours> cours` (`@OneToMany(mappedBy="semestre")`)   |
| 5 | Cours | SessionCours | `1 – 0..*` | oneToMany/ManyToOne | la cle du cours migre vers `sessionCours` porte `cours:Cours`(`@ManytoOne` + `@JoinColumn(name="cours_id")`et dans sessionCours on aura `cours` porte `List<SessionCours> sessionCours` (`@OneToMany(mappedBy="cours")`) |
| 6 | AnneeScolaire | Semestre | `1 – 0..*` | oneToMany/ManyToOne |la cle du AnneeScolaire migre vers `semestre` porte `anneeScolaire:AnneeScolaire`(`ManyToOne` + `@joincolumn(name="anneeScolaire_id`) et dans  AnneeScolaire on aura `AnneeScolaire` porte `List<semestre> Semestre`(`@OneToMany(mappedBy="anneeScolaire")`) |
| 7 | Filiere | Classe | `1 – 0..*` | oneToMany/ManyToOne |la cle du Filiere migre vers `Classe` porte `filiere:Filiere`(`ManyToOne` + `@joincolumn(name="Filiere_id`) et dans  Filiere on aura `Classe` porte `List<Classe> classes`(`@OneToMany(mappedBy="filiere")`)|
| 8 | Niveau | Classe | `1 – 0..*` | oneToMany/ManyToOne | la cle du Niveau migre vers `Classe` porte `niveau:Niveau`(`ManyToOne` + `@joincolumn(name="Niveau_id`) et dans Niveau on aura `Classe` porte `List<Classe> classe`(`@OneToMany(mappedBy="niveau")`)|
| 9 | Classe | Etudiant | `1 – 0..*` | oneToMany/ManyToOne| la cle de Classe migre vers `Etudiant` porte `classe:Classe`(`ManyToOne` + `@joincolumn(name="classe_id`) et dans  classe on aura `Classe` porte `List<Etudiant> etudiant`(`@OneToMany(mappedBy="classe")`)|
| 10 | Classe | AnneeScolaire | `0..* – 0..*` | ManyToMaany| ici on cree une table une table d'association entre les deux nommes ClasseAnneeScolaire et on mets leurs cles primaires de chacun de ces deux tables dans la table associative et un autre attribut qui est nombre de places stockes ici |
| 11 | Cours | Classe | `1..* – 0..*` | ManyToMaany| ici on cree une table une table d'association entre les deux nommes CoursClasse et on mets leurs cles primaires de chacun de ces deux tables dans la table associative   |

## Pourquoi des classes d'association pour les lignes 10 et 11
10-L'effectifPrevu va dans ClasseAnneeScolaire. parce que une seule colonne ne peut pas avoir deux valeurs en meme temps forcement l'une sera ecrasait .
11- jpa cree automatiquement la jointure entre ces deux tables et une table cree automatiquement n'a pas de isActive ,C'est pour ça qu'on crée CoursClasse  

---
