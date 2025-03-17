# Application de calcul d'absorption de CO2 par les forêts

Cette application met en œuvre une API REST permettant de gérer des arbres et des forêts ainsi que de calculer leur capacité d'absorption de CO2.

## Description du projet

L'application permet de :
- Gérer des arbres (création, lecture, mise à jour, suppression)
- Gérer des forêts (création, lecture, mise à jour, suppression)
- Calculer la capacité d'absorption de CO2 d'une forêt en kg/an
- Calculer la surface de forêt nécessaire pour absorber une quantité donnée de CO2
- Obtenir la liste des espèces d'arbres présentes dans une forêt

## Architecture

L'application suit une architecture hexagonale (ports et adaptateurs) :

- **Domain** : Contient les modèles de données et la logique métier
  - `model` : Définition des structures de données (Tree, Forest, Species, etc.)
  - `service` : Implémentation des cas d'utilisation métier
- **Ports** : Points d'entrée et de sortie du domaine
  - `driving` : Interfaces pour les cas d'utilisation (TreeUseCase, ForestUseCase, etc.)
  - `driven` : Interfaces pour les repositories (TreeRepositoryPort, ForestRepositoryPort)
- **Infrastructure** : Implémentation des repositories (en mémoire)
- **API** : Contrat OpenAPI pour l'API REST
- **Presentation** : Contrôleurs REST implémentant l'API
- **Application** : Point d'entrée de l'application

## Algorithmique et modèle de calcul

Le calcul de l'absorption de CO2 par une forêt prend en compte :
1. L'absorption individuelle de chaque arbre (carbonStorageCapacity)
2. Un facteur de diversité : plus il y a d'espèces différentes, plus l'absorption est efficace
3. L'impact du type de forêt : les forêts tropicales sont les plus efficaces, suivies des forêts tempérées et boréales

## Installation et exécution

### Prérequis
- Java 21
- Gradle

### Installation
```bash
gradle wrapper
```

### Construction et tests
```bash
./gradlew build
```

### Exécution
```bash
./gradlew :forest-app:app:bootRun
```

## Utilisation de l'API

Une fois l'application lancée, vous pouvez interagir avec l'API via les endpoints suivants :

### Arbres (Tree)
- `GET /tree` : Liste tous les arbres
- `GET /tree/{id}` : Obtient les détails d'un arbre spécifique
- `POST /tree` : Crée un nouvel arbre
- `PUT /tree/{id}` : Met à jour un arbre existant
- `DELETE /tree/{id}` : Supprime un arbre

### Forêts (Forest)
- `GET /forest` : Liste toutes les forêts
- `GET /forest/{id}` : Obtient les détails d'une forêt spécifique
- `POST /forest` : Crée une nouvelle forêt
- `PUT /forest/{id}` : Met à jour une forêt existante
- `DELETE /forest/{id}` : Supprime une forêt
- `GET /forest/{id}/species` : Liste toutes les espèces d'arbres dans une forêt

### Absorption de CO2
- `GET /forest/{id}/absorption` : Calcule la capacité d'absorption de CO2 d'une forêt
- `GET /absorption/required-surface?co2Amount=X` : Calcule la surface de forêt nécessaire pour absorber X kg de CO2 par an

### Exemple de requête
```bash
curl localhost:8080/tree
```

## Améliorations possibles

Cette application pourrait être enrichie avec :
- La prise en compte de l'âge des arbres dans le calcul d'absorption
- La modélisation des activités humaines (coupes de forêts, reforestation, etc.)
- L'intégration de données météorologiques pour affiner les calculs d'absorption
- Une interface utilisateur pour visualiser les forêts et leur impact environnemental