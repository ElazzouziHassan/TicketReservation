# Service de Réservation de Billets - API SOAP

Une API SOAP complète pour un service de réservation de billets, développée en Java avec JAX-WS et PostgreSQL.

## Table des matières

- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Technologies utilisées](#technologies-utilisées)
- [Structure du projet](#structure-du-projet)
- [Installation](#installation)
- [Configuration](#configuration)
- [Compilation et construction](#Compilation-et-construction)
- [Exécution](#exécution)
- [Test de l'API](#test-de-lapi)
- [Documentation de l'API](#documentation-de-lapi)
- [Schéma de la base de données](#schéma-de-la-base-de-données)
- [Recommandations pour la production](#Recommandations-pour-la-production)
- [Contribution](#contribution)

## Aperçu

Ce projet implémente une API SOAP pour un service de réservation de billets d'événements. Il permet aux utilisateurs de consulter les événements disponibles, de réserver des billets, d'annuler ou de modifier des réservations, et gère l'authentification des utilisateurs.

## Fonctionnalités

- **Gestion des utilisateurs** : Inscription, connexion et récupération des informations utilisateur
- **Consultation des événements** : Liste des événements et billets disponibles
- **Réservation de billets** : Création de réservations pour des événements spécifiques
- **Gestion des réservations** : Annulation et consultation des réservations

## Technologies utilisées

- **Java 11** : Langage de programmation principal
- **JAX-WS** : API Java pour les services web SOAP
- **JPA/Hibernate** : Persistance des données et ORM
- **PostgreSQL** : Base de données relationnelle
- **Maven** : Gestion des dépendances et construction du projet

## Structure du projet

```xml
src/main/java/com/ticketservice/
├── client/
│   └── SoapClient.java         # Client de test pour les services SOAP
├── model/
│   ├── Utilisateur.java        # Entité utilisateur
│   ├── Evenement.java          # Entité événement
│   ├── Billet.java             # Entité billet
│   └── Reservation.java        # Entité réservation
├── repository/
│   ├── DatabaseConnection.java # Gestion de la connexion à la base de données
│   ├── UtilisateurRepository.java
│   ├── BilletRepository.java
│   └── ReservationRepository.java
├── service/
│   ├── UtilisateurService.java # Service SOAP pour la gestion des utilisateurs
│   ├── BilletService.java      # Service SOAP pour la gestion des billets
│   └── ReservationService.java # Service SOAP pour la gestion des réservations
└── Main.java                   # Point d'entrée de l'application

src/main/resources/
└── META-INF/
    └── persistence.xml         # Configuration JPA
```

## Installation

### Prérequis

- Java JDK 11 ou supérieur
- Maven 3.6 ou supérieur
- PostgreSQL 12 ou supérieur

### Clonage du dépôt

```bash
  SSH        : git@github.com:ElazzouziHassan/TicketReservation.git
  HTTPS      : https://github.com/ElazzouziHassan/TicketReservation.git
  GitHub CLI : gh repo clone ElazzouziHassan/TicketReservation
  
  cd TicketReservation
```

### Installation des dépendances

```bash
  mvn clean install
```

## Configuration

### Base de données

1. Créez une base de données PostgreSQL :

```bash
  # Se connecter à PostgreSQL
  sudo -i -U postgres psql

  # Créer la base de données
  CREATE DATABASE ticketservice;

  # Quitter psql
  \q
```

2. Configurez les informations de connexion dans `src/main/resources/META-INF/persistence.xml` :

```xml
  <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/ticketservice" />
  <property name="javax.persistence.jdbc.user" value="postgres" />
  <property name="javax.persistence.jdbc.password" value="votre_mot_de_passe" />
```

## Compilation et construction

### Création du JAR exécutable

```bash
  mvn clean package
```

Cette commande générera deux fichiers JAR dans le dossier `target/` :
- `ticketservice-1.0-SNAPSHOT.jar` : JAR standard
- `ticketservice-1.0-SNAPSHOT-jar-with-dependencies.jar` : JAR exécutable avec toutes les dépendances

## Exécution

### Démarrage du serveur SOAP

```bash
  java -jar target/ticketservice-1.0-SNAPSHOT-jar-with-dependencies.jar     
```

Les services SOAP seront disponibles aux adresses suivantes :
- http://localhost:8181/UtilisateurService?wsdl
- http://localhost:8181/BilletService?wsdl
- http://localhost:8181/ReservationService?wsdl

### Exécution du client de test

```bash
  java -cp target/ticketservice-1.0-SNAPSHOT-jar-with-dependencies.jar com.ticketservice.client.SoapClient
```

## Test de l'API

### Avec SoapUI

1. Téléchargez et installez [SoapUI](https://www.soapui.org/downloads/soapui/)
2. Créez un nouveau projet SOAP
3. Ajoutez les WSDL de vos services :
   - http://(Machine IP adresse):8181/UtilisateurService?wsdl
   - http://(Machine IP adresse):8181/BilletService?wsdl
   - http://(Machine IP adresse):8181/ReservationService?wsdl

### Exemples de requêtes SOAP

#### Inscription d'un utilisateur

```xml
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.ticketservice.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:inscrireUtilisateur>
          <email>test@mail.com</email>
          <nom>test</nom>
          <prenom>user</prenom>
          <motDePasse>pass1234</motDePasse>
        </ser:inscrireUtilisateur>
    </soapenv:Body>
  </soapenv:Envelope>
```

#### Connexion d'un utilisateur

```xml
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.ticketservice.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:connecterUtilisateur>
          <email>test@mail.com</email>
          <motDePasse>pass1234</motDePasse>
        </ser:connecterUtilisateur>
    </soapenv:Body>
  </soapenv:Envelope>
```

#### Récupération des billets

```xml
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.ticketservice.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:getBillets/>
    </soapenv:Body>
  </soapenv:Envelope>
```

## Documentation de l'API

### UtilisateurService

| Méthode | Description | Paramètres | Retour |
|---------|-------------|------------|--------|
| inscrireUtilisateur | Inscription d'un nouvel utilisateur | email, nom, prenom, motDePasse | boolean |
| connecterUtilisateur | Connexion d'un utilisateur | email, motDePasse | Utilisateur |
| getUtilisateurById | Récupération des informations d'un utilisateur | utilisateurId | Utilisateur |

### BilletService

| Méthode | Description | Paramètres | Retour |
|---------|-------------|------------|--------|
| getBillets | Récupération de tous les billets disponibles | - | List<Billet> |
| getBilletById | Récupération d'un billet spécifique | billetId | Billet |
| getBilletsByEvenementId | Récupération des billets pour un événement | evenementId | List<Billet> |
| updateQuantiteDisponible | Mise à jour de la quantité disponible | billetId, nouvelleQuantite | boolean |

### ReservationService

| Méthode | Description | Paramètres | Retour |
|---------|-------------|------------|--------|
| reserverBillet | Réservation d'un billet | utilisateurId, billetId, quantite | boolean |
| annulerReservation | Annulation d'une réservation | reservationId | boolean |
| getReservationsUtilisateur | Récupération des réservations d'un utilisateur | utilisateurId | List<Reservation> |

## Schéma de la base de données

Le schéma de la base de données comprend quatre tables principales :

1. **utilisateurs** : Stocke les informations des utilisateurs
   - id (PK)
   - email (unique)
   - nom
   - prenom
   - motDePasse

2. **evenements** : Stocke les informations des événements
   - id (PK)
   - nom
   - description
   - date_heure
   - lieu
   - capacite_max

3. **billets** : Stocke les informations des billets disponibles
   - id (PK)
   - evenement_id (FK)
   - categorie
   - prix
   - quantite_disponible

4. **reservations** : Stocke les informations des réservations
   - id (PK)
   - utilisateur_id (FK)
   - billet_id (FK)
   - quantite
   - date_reservation
   - statut

## Recommandations pour la production

Pour un déploiement en production, considérez les améliorations suivantes :

### 1. Sécurité

- **Hachage des mots de passe** : Utilisez BCrypt ou Argon2 pour stocker les mots de passe de manière sécurisée
- **Authentification par jeton** : Implémentez JWT pour une authentification plus sécurisée
- **HTTPS** : Configurez TLS/SSL pour les services SOAP
- **Validation des entrées** : Ajoutez une validation robuste des données d'entrée

### 2. Performance

- **Connection Pooling** : Remplacez le pool de connexions Hibernate par HikariCP
- **Mise en cache** : Implémentez Hibernate Second Level Cache avec EhCache ou Redis
- **Optimisation des requêtes** : Utilisez des requêtes optimisées et des index de base de données

### 3. Robustesse

- **Gestion d'erreurs** : Implémentez une gestion d'erreurs plus sophistiquée avec des exceptions personnalisées
- **Tests** : Ajoutez des tests unitaires et d'intégration avec JUnit et Mockito

## Contribution

Les contributions sont les bienvenues ! Voici comment contribuer :

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/new-feature`)
3. Committez vos changements (`git commit -m 'Add some a new feature'`)
4. Poussez vers la branche (`git push origin feature/new-feature`)
5. Ouvrez une Pull Request

---
@all the Best
