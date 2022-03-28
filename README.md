# Projet Library Manager IN205
*Author:* Bruno Macedo Sanches

Ce projet a été développée pour la discipline IN205 - Applications portables à l'ENSTA Paris

## Installation des libraries et compilation du projet

Pour installer les libraries necessáires et compiler le projet, utilisez

```
mvn clean install compile
```

## Initialisation de la base de données

Pour initialiser les tables de la base de données et ajouter des données d'exemple, utilisez

```
mvn exec:java
```

## Exécution de l'application Web

Pour lancer l'application web, utilisez

```
mvn tomcat7:run
```

Aprés l'exécution de l'application web, il est possible d'acceder au site en utilisant le url http://localhost:8080/TP3Ensta/.

## Tests

Tous les tests crées pour les services et le DAO peuvent êtres éxécutes en utilisant la commande

```
mvn test
```

