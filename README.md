# Gestion des cours - Ecole 221

API REST de planification des cours (Spring Boot).

## Stack

| Élément | Version |
|---|---|
| Java | 17 |
| Spring Boot | 4.1.2  à la place de 3.5.x) |
| Base de données | MySQL 8 |
| Build | Maven, via le wrapper `./mvnw` |
| Port de l'API | 8080 |

## Organisation du dépôt

```
gestion_cours/
├── README.md
├── docs/            diagrammes et tableau des relations
├── githooks/        hook pre-commit versionné
└── planification/   projet Spring Boot
```

## Prérequis

- **Java 17** (vérifier avec `java -version`)
- **MySQL 8**, démarré
- Maven

## 1. Créer la base de données

Avec un compte administrateur MySQL (par exemple `root` dans MySQL Workbench), exécuter en
remplaçant `***` par un mot de passe de votre choix :

```sql
CREATE DATABASE planification
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE USER 'planif_modou'@'localhost' IDENTIFIED BY '***';
GRANT ALL PRIVILEGES ON planification.* TO 'planif_modou'@'localhost';
FLUSH PRIVILEGES;
```

L'application se connecte avec cet utilisateur dédié, qui n'a des droits que sur la base
`planification`, et non avec `root`.

## 2. Configurer l'accès à la base

Le mot de passe MySQL n'est jamais écrit dans un fichier versionné.
`planification/src/main/resources/application.properties` ne contient que des références :

```properties
spring.config.import=optional:classpath:application-local.properties
spring.datasource.url=jdbc:mysql://localhost:3306/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Les valeurs sont dans un fichier local, **ignoré par git** (voir `.gitignore`). Pour le créer,
copier le modèle fourni puis le remplir :

```powershell
cd planification/src/main/resources
copy application-local.properties.example application-local.properties
```


**Comment ça marche.** La ligne `spring.config.import=optional:classpath:application-local.properties`
demande à Spring de lire aussi ce fichier au démarrage. Spring remplace alors chaque `${DB_NAME}`,
`${DB_USER}` et `${DB_PASSWORD}` par la valeur trouvée. Le préfixe `optional:` évite une erreur si
le fichier est absent.

**Pourquoi ce choix.** Des variables d'environnement auraient aussi fonctionné, mais il aurait fallu
les redéfinir dans chaque nouveau terminal. Le fichier local se configure une seule fois. Le risque,
le commiter par erreur, est couvert par le `.gitignore` et par la règle 1 du hook.

## 3. Lancer l'application

```powershell
cd planification
./mvnw spring-boot:run
```

L'application est prête quand la console affiche :

```
HikariPool-1 - Start completed.
Tomcat started on port 8080 (http)
Started PlanificationApplication in ... seconds
```

`HikariPool-1 - Start completed` confirme la connexion à MySQL. Arrêter avec `Ctrl+C`.

### Jeu de données de départ

Au premier démarrage, la classe `config/DataInitializer` (un `CommandLineRunner`) insère
un jeu de test : une année scolaire courante (2026-2027), un semestre, 3 filières, 5 niveaux,
5 classes, 2 professeurs, 2 salles, 3 modules et 3 étudiants.

**Rejouable** : si une année scolaire existe déjà en base, rien n'est inséré. La console
affiche alors `Jeu de donnees deja present : aucune insertion.`

**Pourquoi un `CommandLineRunner` plutôt que `data.sql`** : les insertions passent par les
repositories, donc `@PrePersist` remplit `createdAt` et `isActive` automatiquement, et les
relations utilisent des objets vérifiés par le compilateur au lieu d'identifiants écrits en dur.

## Utiliser l'API

Toutes les réponses ont la même forme. Quand tout se passe bien, on reçoit le code HTTP et le
résultat dans `results` :

```json
{ "status": 200, "results": [ ... ] }
```

Quand quelque chose ne va pas, on reçoit le code d'erreur et un message en français qui explique
le problème :

```json
{ "status": 404, "message": "Module introuvable : 999" }
```

### Les routes disponibles

| Méthode | Route | À quoi elle sert |
|---|---|---|
| GET | `/api/v1/modules` | lister les modules actifs |
| GET | `/api/v1/modules/{id}` | consulter un module précis |
| POST | `/api/v1/cours` | planifier un nouveau cours |

### Planifier un cours

On envoie le quota d'heures, le module, le professeur, le semestre et les classes concernées :

```http
POST http://localhost:8080/api/v1/cours
Content-Type: application/json

{
  "nombreHeureGlobal": 60,
  "moduleId": 2,
  "professeurId": 1,
  "semestreId": 1,
  "classeIds": [1, 2]
}
```

Si tout est correct, l'API répond `201` avec le cours créé. Il démarre à l'état `EN_COURS`, et
chaque classe de la liste lui est rattachée.

Avant de créer quoi que ce soit, l'API vérifie la demande et la refuse dans les cas suivants :

| Situation | Réponse |
|---|---|
| le module, le professeur ou le semestre n'existe pas | `404`, par exemple « Module introuvable : 3 » |
| une des classes n'existe pas | `404`, par exemple « Classe introuvable : 7 » |
| la liste des classes est vide ou absente | `400` « Un cours doit s'adresser a au moins une classe » |
| le nombre d'heures manque ou n'est pas positif | `400`, avec le détail du champ en cause |
| le semestre n'appartient pas à l'année scolaire courante | `400` « Le semestre 2 n'appartient pas a l'annee scolaire courante » |

La création est faite d'un seul bloc : si une classe est introuvable au milieu de la liste, rien
n'est enregistré, pas même le cours.

### Tester l'API

Le fichier `planification/api-tests.http` contient toutes ces requêtes, cas d'erreur compris.
Dans IntelliJ, on l'ouvre et on clique sur la flèche à côté de chaque requête (ou sur la double
flèche en haut pour tout lancer d'un coup). L'application doit être démarrée.

Pour le test du semestre hors de l'année courante, il faut un semestre rattaché à une année qui
n'est pas courante. Le jeu de données n'en contient pas : on peut l'ajouter dans MySQL avec

```sql
INSERT INTO annee_scolaire (libelle, date_debut, date_fin, est_courante, is_active, created_at)
VALUES ('2025-2026', '2025-09-01', '2026-07-31', false, true, NOW());

INSERT INTO semestre (libelle, date_debut, date_fin, annee_scolaire_id, is_active, created_at)
VALUES ('Semestre 1', '2025-09-01', '2026-01-31', LAST_INSERT_ID(), true, NOW());
```

### Pourquoi certaines relations n'apparaissent pas dans le JSON

Pour l'instant, l'API renvoie directement les entités. Or beaucoup de relations existent dans
les deux sens : un cours connaît son semestre, et le semestre connaît la liste de ses cours.
En transformant un cours en JSON, on passerait donc du cours au semestre, du semestre à ses
cours, de ces cours à leur semestre, et ainsi de suite sans fin. C'est ce qui s'est produit au
premier essai : la réponse faisait plus de 80 ko et s'arrêtait en plein milieu.

Pour casser ces boucles, on a masqué le côté « liste » de chaque relation avec `@JsonIgnore`.
Un cours affiche donc toujours son module, son semestre et son professeur, mais un semestre
n'affiche plus la liste de ses cours.

| Entité | Champs masqués |
|---|---|
| `AnneeScolaire` | `semestres`, `classesAnneeScolaire` |
| `Semestre` | `cours` |
| `Filiere` | `classes` |
| `Niveau` | `classes` |
| `Classe` | `etudiants`, `classesAnneeScolaire`, `coursClasses` |
| `Module` | `cours` |
| `Professeur` | `cours` |
| `Cours` | `sessionCours`, `coursClasses` |
| `Salle` | `sessionCours` |

Il y avait aussi un champ technique, `hibernateLazyInitializer`, qui apparaissait dans certaines
réponses. Il vient d'Hibernate, qui ne charge certaines données qu'au moment où on en a besoin.
Il est masqué une fois pour toutes avec `@JsonIgnoreProperties` sur `AbstractEntity`.

Ces deux annotations sont une solution provisoire. Au lot 2, l'API renverra des objets de réponse
dédiés (les DTO) au lieu des entités, et le problème disparaîtra à la source.

## 4. Activer le hook pre-commit

À faire **une fois par clone**, depuis la racine du dépôt :

```bash
git config core.hooksPath githooks
```

Vérification : `git config core.hooksPath` doit répondre `githooks`.

Le hook `githooks/pre-commit` refuse un commit dont les lignes ajoutées contiennent :

| Règle | Ce qui est refusé | À la place |
|---|---|---|
| 1 | un secret en clair (`password`, `secret`, `token`, `apikey`) dans un `.properties`, `.yml`, `.yaml` ou `.java` | une référence `${VARIABLE}` |
| 2 | `@Autowired` | `@RequiredArgsConstructor` et des champs `private final` |
| 3 | `System.out.print` | `@Slf4j` et `log.info(...)` |
| 4 | `throw new RuntimeException` | une exception de `exceptions/` qui porte le bon code HTTP |
| 5 | une icône ou un émoji, dans n'importe quel fichier | une phrase |

Ne pas contourner le hook avec `git commit --no-verify`.

## Diagramme de cas d'utilisation

![Diagramme de cas d'utilisation](docs/cas-utilisation.jpg)

## Diagramme de classes

![Diagramme de classes](docs/diagrammedeclasse.jpg)

Source StarUML des deux diagrammes : [docs/diagrammedeclasse.mdj](docs/diagrammedeclasse.mdj)
