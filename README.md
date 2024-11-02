# Gestion des Locations de Voitures et des Clients

## Table des matières
1. [Description](#description)
2. [Fonctionnalités](#fonctionnalités)
3. [Architecture du Projet](#architecture-du-projet)
4. [Entités](#entités)
5. [Technologies Utilisées](#technologies-utilisées)
6. [Installation](#installation)
7. [Lien vers la vidéo de démonstration](#lien-vers-la-vidéo-de-démonstration)


## Description
Ce projet est une application web de gestion des locations de voitures. Elle permet aux utilisateurs de consulter les voitures disponibles à la location, de visualiser les détails de chaque voiture, et de gérer les locations effectuées par les clients. Le système prend en charge plusieurs clients et leurs locations respectives.

## Fonctionnalités
- **Page Liste des Voitures** : 
  - Affiche toutes les voitures disponibles à la location avec leurs détails (modèle, immatriculation, prix de location).
  - Permet de modifier, supprimer ou louer une voiture si celle-ci est disponible.
    
- **Page Détail des Locations** : 
  - Montre toutes les locations effectuées par un client, avec les dates de début et de fin, ainsi que les voitures concernées.
  - Permet de modifier ou de supprimer une location.

## Architecture du Projet
Le projet est construit sur une architecture MVC (Modèle-Vue-Contrôleur) :
- **Modèle** : Définit les entités `Voiture` et `Location`.
- **Vue** : Utilise Thymeleaf pour générer les pages HTML dynamiques.
- **Contrôleur** : Gère la logique métier et la communication entre le modèle et la vue.

## Entités
### Voiture
- **id** : Identifiant unique de la voiture (généré automatiquement).
- **modèle** : Le modèle de la voiture (ex : "Toyota Corolla").
- **immatriculation** : Numéro d'immatriculation de la voiture (ex : "AB-123-CD").
- **prixDeLocation** : Prix de location par jour (ex : 500 DH).
- **disponible** : Statut indiquant si la voiture est disponible à la location (boolean).

### Location
- **id** : Identifiant unique de la location (généré automatiquement).
- **dateDebut** : Date à laquelle la location commence.
- **dateFin** : Date à laquelle la location se termine.
- **voiture** : Référence à l'objet Voiture qui est loué.
- **client** : Nom du client qui a effectué la location.

## Technologies Utilisées
- **Java** : Langage de programmation principal.
- **Spring Boot** : Framework utilisé pour le développement d'applications Java simplifiées.
- **Thymeleaf** : Moteur de templates pour le rendu dynamique des pages web.
- **MySQL** : Système de gestion de base de données pour stocker les informations sur les voitures et les locations.
- **Maven** : Outil de gestion de projet et d'automatisation de build pour Java.

## Installation
### Prérequis
- Java 11 ou version ultérieure.
- MySQL installé et configuré.
- Maven installé pour gérer les dépendances.

## Utilisation
- Pour louer une voiture, naviguer vers la Liste des Voitures et sélectionner la voiture souhaitée.
-Pour voir les détails des locations, aller à la Détail des Locations.

## lien vers la vidéo de démonstration :
https://drive.google.com/file/d/1pPRi2A1nhJJpzvJdRy4VhDTwBK8Onm7u/view?usp=sharing
 





