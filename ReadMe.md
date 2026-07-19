
# Dairy Farm Management System

Het **Dairy Farm Management System** maakt een einde aan papieren logboeken in de stal. Deze centrale Java Spring Boot applicatie digitaliseert de volledige medicatieregistratie en klauwgezondheid van uw veestapel. Door alle data op één herkenbare plek te verzamelen, behoudt de veehouder altijd het overzicht.

---

## Kernfunctionaliteiten

* **Koeienbeheer**: Koeien eenvoudig registreren, beheren en hun gegevens direct in de stal bijwerken.
* **Medicatiebeheer**: Informatie over medicijnen en doseringen snel invoeren en aanpassen.
* **Behandelingslogboek**: Behandelingen registreren (inclusief handige multi-dag functionaliteit) en de geschiedenis per koe bekijken.
* **Klauwbehandelingen**: Alle specifieke klauwbehandelingen en aandoeningen (zoals Mortellaro) nauwkeurig vastleggen.
* **Visuele Identificatie**: Bij elke koe een actuele foto toevoegen voor snelle herkenning aan het voerhek.
* **Medicatie-inventaris**: Altijd een actueel overzicht van de exacte medicatievoorraad op het bedrijf.

---

## Technische Stack & Benodigdheden

Om dit project te kunnen bouwen en runnen, heeft u de volgende software en versies nodig:

* **Runtime Environment**: Java Development Kit (JDK) 21
* **Framework**: Spring Boot 4.1.0 (met Spring Security & OAuth2)
* **Build Tool**: Maven 
* **Databases**:
    * PostgreSQL (voor productie)
    * H2 Database (test-runs)
* **Libraries**: Lombok, MapStruct, Springdoc OpenAPI voor Swagger UI)

## Projectstructuur

Het project is opgebouwd volgens de standaard Spring Boot gelaagde architectuur:

```text
dairy-farm/
├── src/
│   ├── main/
│   │   ├── java/nl/wiegersma/dairyfarm/
│   │   │   ├── DairyFarmApplication.java   # Startklasse van de app
│   │   │   ├── config/                    # Security & Swagger configuratie
│   │   │   ├── controllers/               # REST API Endpoints (Koe, Behandeling, etc.)
│   │   │   ├── services/                  # Business logica (bijv. multi-dag logica)
│   │   │   ├── repositories/              # Database interfaces (Spring Data JPA)
│   │   │   ├── models/                    # Database entiteiten / Tabellen
│   │   │   ├── dtos/                      # Data-objecten voor API requests/responses
│   │   │   └── mappers/                   # MapStruct mappers (DTO <-> Model)
│   │   └── resources/
│   │       └── application.properties     # Database- en applicatie-instellingen
│   └── test/                              # Unit- en integratietesten
└── pom.xml                                # Maven configuratie en dependencies
```

---

## Aan de slag

### 1. Database configuratie
Zorg dat er een PostgreSQL database draait noem hem bijvoorbeeld `dairy_farm_management` en pas de inloggegevens aan in `src/main/resources/application.properties`:
```properties
PSQL_DATABASENAME=Uw DatabaseNaam
PSQL_USERNAME=Uw gebruikersnaam
PSQL_USERPASSWORD=Uw wachtwoord
```

### 2. Start Keycloak

Dit project maakt gebruik van Keycloak voor de authenticatie en beveiliging. Zorg ervoor dat Keycloak draait voordat je de Spring Boot backend opstart:

1. Open een nieuwe terminal of opdrachtprompt (CMD).
2. Navigeer naar de map van je lokale Keycloak-installatie.
3. Start Keycloak in development-modus op poort 9090 met het volgende commando:
   ```cmd
   bin\kc.bat start-dev --http-port 9090
   ```


### 2. Applicatie opstarten
Gebruik de Maven wrapper in de hoofdmap om de applicatie te starten:

* **Windows:**
  ```bash
  mvnw.cmd spring-boot:run
  ```
* **Linux / macOS:**
  ```bash
  ./mvnw spring-boot:run
  
* **Opstarten via IntelliJ IDEA (Aanbevolen)**

Voor een soepele ontwikkelomgeving start u de applicatie het beste rechtstreeks vanuit uw code-editor op:

1. **Project openen**: Open de hoofdmap van het project in IntelliJ IDEA.
2. **Startklasse zoeken**: Navigeer in de projectstructuur aan de linkerkant naar het volgende bestand:
   `src` ➡️ `main` ➡️ `java` ➡️ `nl.wiegersma.dairyfarm` ➡️ `DairyFarmApplication.java`
3. **Applicatie runnen**: Open dit bestand en klik op de **groene "Play"-knop** (het driehoekje) die direct links naast de `main`-methode staat:
   ```java
   public static void main(String[] args);
   ```
4. **Uitvoeren**: Kies in het menu dat verschijnt voor **Run 'DairyFarmManagementSystem'**.


### 3.  API Documentatie (Swagger)
Zodra de applicatie draait, is de interactieve API-documentatie te vinden op:
`http://localhost:8080/swagger-ui.html`

### 4. Test-Inloggegevens
Omdat de applicatie is beveiligd, heeft u inloggegevens nodig:

#### Gebruiker 1 (Volledige rechten)
* **Gebruikersnaam:** `Admin`
* **Wachtwoord:** `admin`
* **Rol:** `ADMIN`

#### Gebruiker 2 (Beperkte rechten)
* **Gebruikersnaam:** `Employee`
* **Wachtwoord:** `wachtwoord`
* **Rol:** `EMPLOYEE`
####  OAuth2 / Keycloak Configuratie 

De applicatie maakt gebruik van een lokale Keycloak-instantie op poort `9090` voor de authenticatie. Gebruik de volgende variabelen in Postman of uw frontend om tokens aan te vragen:

* **Auth URL:** `http://localhost:9090/realms/DairyFarmManagementSystem/protocol/openid-connect/auth`
* **Access Token URL:** `http://localhost:9090/realms/DairyFarmManagementSystem/protocol/openid-connect/token`
* **Client ID:** `DairyFarmManagement`
* **Client Secret:** `7OUYg0dfRoPbgNN5KYIS2ETEuvjvxWpu`

---
# Teststrategie

Binnen de map `src/test/java/` zijn de testen verdeeld in twee categorieën:

1. **Unit Testen**:
    * Testen losse stukjes code, In mijn geval ongeveer één test per methode
    * Maken gebruik van mocks (nep-objecten) zodat er geen database-verbinding nodig is.
2. **Integratie Testen (De Ketentest)**:
    * Testen of de lagen (Controller ➡️ Service ➡️ Repository) goed met elkaar samenwerken.
    * Starten een tijdelijke H2-database op om te controleren of een koe daadwerkelijk correct wordt opgeslagen en opgehaald.

---

### 3. Hoe voer je de testen uit?

#### Optie A: Via IntelliJ IDEA 
1. Navigeer in de mappenstructuur aan de linkerkant naar de map `src/test/java/`.
2. Klik met de **rechtermuisknop** op de hoofdmap `nl.wiegersma.dairyfarm`.
3. Kies in het menu voor **Run 'Tests in 'dairy-farm' '**.
4. Onderin uw scherm opent het *Run Dashboard* waarin u direct groene vinkjes ziet verschijnen voor elke geslaagde test.

#### Individueel testen
Je kan ook testen individueel testen door naar een bepaalde test te gaan daarbij op het groene drie hoekje te klikken

#### Optie B: Via de Terminal (Maven Wrapper)
Als u alle testen snel wilt draaien via de command-line (bijvoorbeeld voor een automatische controle voor het inleveren):

* **Windows:**
  ```bash
  .\mvnw.cmd test
  ```
* **Linux / macOS:**
  ```bash
  ./mvnw test
  ```