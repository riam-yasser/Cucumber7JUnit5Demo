# Cucumber 7 + Selenium 4 + JUnit 5 — Automation Framework

Ce projet est un **socle d’automatisation UI** basé sur **Cucumber (BDD)**, **Selenium WebDriver** et **JUnit 5 (JUnit Platform)**.  
L’objectif est d’avoir une architecture claire, maintenable et facile à exécuter en local ou en CI.

---

## 🎯 Objectifs du framework

- Écrire des scénarios BDD lisibles en **Gherkin** (`.feature`)
- Implémenter les steps avec **Cucumber + JUnit 5**
- Automatiser des parcours web via **Selenium 4**
- Séparer les responsabilités avec une architecture simple :
    - **Actions** : logique métier / interactions
    - **Locators** : sélecteurs centralisés
    - **Hooks** : initialisation / fermeture du navigateur
    - **Utils** : helpers & configuration

---

## 🧰 Stack technique

- **Java** : 21
- **Cucumber** : 7.14.0
- **JUnit Jupiter / JUnit Platform** : 5.10.1
- **Selenium** : 4.41.0
- **Maven** : 3.10.0

---

## 📁 Structure du projet
``` 
src
├─ main
│ └─ java
│ └─ com.example
│ ├─ actions
│ │ ├─ HomePageActions.java
│ │ ├─ LoginPageActions.java
│ │ └─ ForgotPasswordActions.java
│ ├─ locators
│ │ ├─ HomePageLocators.java
│ │ ├─ LoginPageLocators.java
│ │ └─ ForgotPasswordLocators.java
│ └─ utils
│ ├─ ConfigFileReader.java
│ └─ HelperClass.java
│
└─ test
├─ java
│ └─ com.example
│ ├─ definitions
│ │ ├─ Hooks.java
│ │ └─ LoginPageDefinitions.java
│ └─ runner
│ └─ CucumberRunnerTests.java
│
└─ resources
├─ features
│ └─ LoginPage.feature
└─ junit-platform.properties
``` 

### 🔎 Rôle des packages

- **actions/** : contient les méthodes “métier” (ex: `login(username, password)`), appelées par les Step Definitions.
- **locators/** : centralise les sélecteurs (id/xpath/css) pour éviter la duplication et faciliter la maintenance.
- **utils/** :
    - `ConfigFileReader` : lecture de configuration (url, browser, timeouts, etc.)
    - `HelperClass` : utilitaires partagés (driver, waits, navigation… selon ton implémentation)
- **definitions/** :
    - `Hooks` : `@Before/@After` pour démarrer/fermer le navigateur, screenshots en cas d’échec, etc.
    - `LoginPageDefinitions` : mapping Gherkin → code (steps Cucumber)
- **runner/** : classe JUnit 5 qui lance Cucumber (`CucumberRunnerTests`)
- **features/** : scénarios BDD (`LoginPage.feature`)
- **junit-platform.properties** : configuration de l’exécution via JUnit Platform (glue, plugins, tags, etc.)

---

## ✅ Prérequis

- **Java 21** installé
- **Maven** installé

Vérifier les versions :

```bash
java -version
mvn -version
```
## ▶️ Exécution des tests

### 1) Lancer toute la suite (recommandé)
Cette commande exécute le cycle Maven complet (**clean + verify**).  
Elle est idéale pour exécuter toute la suite et générer les rapports via le cycle standard.

```bash
mvn clean verify
```
### 2) Lancer toute la suite (recommandé)
Cette commande lance l’exécution des tests dans la phase test (plus rapide en local).
À utiliser quand tu veux juste tester rapidement sans passer par toutes les vérifications du cycle verify

```bash
mvn clean test
```

### 3) Exécuter uniquement les scénarios @smoke
Lance uniquement les scénarios marqués avec le tag @smoke.
Utile pour une validation rapide après un changement (sanity check)

```bash
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

### 4) Exécuter @regression en excluant @wip
Lance tous les scénarios taggés @regression, mais ignore ceux taggés @wip (Work In Progress).
Pratique quand tu veux exécuter la régression complète sans les tests en cours de développement.
```bash
mvn clean verify -Dcucumber.filter.tags="@regression and not @wip"
```