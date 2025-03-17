# 🌳 Application de calcul d'absorption de CO2 par les forêts

> Application REST permettant de gérer des arbres et des forêts, avec fonctionnalité de calcul d'absorption de CO2 (en développement).

## 📋 Description du projet

Cette application vous permet de :
- 🌱 Gérer des arbres (création, lecture, mise à jour, suppression)
- 🌲 Gérer des forêts (création, lecture, mise à jour, suppression)
- 🌿 Visualiser les espèces d'arbres présentes dans une forêt
- 🔄 *Fonctionnalités en cours de développement:*
  - Calcul de la capacité d'absorption de CO2 d'une forêt en kg/an
  - Calcul de la surface de forêt nécessaire pour absorber une quantité donnée de CO2

## 🏗️ Architecture

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

## 🧮 Modèle de calcul (en développement)

> ⚠️ **Note:** La fonctionnalité de calcul d'absorption de CO2 est actuellement en cours d'implémentation.

Le modèle prévoit de prendre en compte :
1. L'absorption individuelle de chaque arbre (carbonStorageCapacity)
2. Un facteur de diversité : plus il y a d'espèces différentes, plus l'absorption est efficace
3. L'impact du type de forêt : les forêts tropicales sont les plus efficaces, suivies des forêts tempérées et boréales

## 🚀 Installation et exécution

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

## 🔌 Utilisation de l'API

Une fois l'application lancée, vous pouvez interagir avec l'API via les endpoints suivants :

### 🌱 Arbres (Tree)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/tree` | Liste tous les arbres |
| GET | `/tree/{id}` | Obtient les détails d'un arbre spécifique |
| POST | `/tree` | Crée un nouvel arbre |
| PUT | `/tree/{id}` | Met à jour un arbre existant |
| DELETE | `/tree/{id}` | Supprime un arbre |

### 🌲 Forêts (Forest)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/forest` | Liste toutes les forêts |
| GET | `/forest/{id}` | Obtient les détails d'une forêt spécifique |
| POST | `/forest` | Crée une nouvelle forêt |
| PUT | `/forest/{id}` | Met à jour une forêt existante |
| DELETE | `/forest/{id}` | Supprime une forêt |
| GET | `/forest/{id}/species` | Liste toutes les espèces d'arbres dans une forêt |

### 🌿 Absorption de CO2 (en développement)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/forest/{id}/absorption` | Calcule la capacité d'absorption de CO2 d'une forêt |
| GET | `/absorption/required-surface?co2Amount=X` | Calcule la surface de forêt nécessaire pour absorber X kg de CO2 par an |

## 📝 Exemples d'utilisation

### Création d'un arbre
```bash
curl -X POST \
  http://localhost:8080/tree \
  -H 'Content-Type: application/json' \
  -d '{
    "birth": "2020-03-15",
    "exposure": "SHADOW",
    "species": "OAK",
    "carbonStorageCapacity": 35.5
  }'
```

### Mise à jour d'un arbre
```bash
curl -X PUT \
  http://localhost:8080/tree/{ID} \
  -H 'Content-Type: application/json' \
  -d '{
    "birth": "2020-01-15",
    "exposure": "SHADOW",
    "species": "FIR",
    "carbonStorageCapacity": 30.5
  }'
```

### Création d'une forêt
```bash
curl -X POST \
  http://localhost:8080/forest \
  -H 'Content-Type: application/json' \
  -d '{
    "type": "TEMPERATE",
    "surface": 5000.0,
    "trees": [
      {
        "birth": "2015-05-20",
        "exposure": "SUNNY",
        "species": "OAK",
        "carbonStorageCapacity": 25.0
      },
      {
        "birth": "2018-03-15",
        "exposure": "MID_SHADOW",
        "species": "FIR",
        "carbonStorageCapacity": 18.5
      }
    ]
  }'

curl -X PUT \
  http://localhost:8080/forest/VOTRE_ID_FORET \
  -H 'Content-Type: application/json' \
  -d '{
    "type": "TEMPERATE",
    "surface": 7500.0,
    "trees": [
      {
        "birth": "2015-05-20",
        "exposure": "SUNNY",
        "species": "OAK",
        "carbonStorageCapacity": 25.0
      },
      {
        "birth": "2018-03-15",
        "exposure": "MID_SHADOW",
        "species": "FIR",
        "carbonStorageCapacity": 18.5
      },
      {
        "birth": "2022-02-10",
        "exposure": "SUNNY",
        "species": "BEECH",
        "carbonStorageCapacity": 15.0
      }
    ]
  }'

```