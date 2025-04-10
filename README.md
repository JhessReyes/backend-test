# **Kotlin Backend with Ktor**
This project is a RESTful API developed in  **Kotlin** using the **Ktor** framework, following **Clean Code** principles. The backend uses **Exposed**  as the ORM to interact with the database and **Flyway** for migration management.

## 👨‍💻 **Technologies**

- **Kotlin**: Modern JVM programming language.
- **Ktor**: Lightweight framework for building asynchronous servers and clients.
- **Exposed**: SQL framework for Kotlin.
- **Flyway**: Database migration tool.
- **PostgreSQL**: Relational database.
- **Docker**: Container platform.
- **Docker Compose**: Multi-container Docker applications.

---
## 🚀 **Get Started**

**Prerequisites**

Docker and Docker Compose installed on your system
Git (optional for cloning)

1. Clone the repository
```bash
  git clone https://github.com/JhessReyes/backend-test/
  cd ./backend-test
```
2. Launch the application with Docker Compose
```bash
  docker-compose up --build
```
The API will be available at http://localhost:8080

---

### ⭐️ Core Functionality

- **Automatic data fetch on startup from external API**  
  Upon application launch, it fetches data from the public Counter-Strike API and stores all relevant records in the local database for exclusive use from that point forward.
![diagram-4](https://github.com/user-attachments/assets/f77c5caa-164f-4c3b-9a15-a5e8276b3448)


- **RESTful API for entity management**  
  Provides endpoints to retrieve data from:
  - 🎯 Skins  
  - 👥 Agents  
  - 📦 Crates  
  - 🔑 Keys  

- **Advanced search and filtering**  
  Allows dynamic filtering by name, description, crateName, etc.  
  Example: `/skins?crateName=Kilowatt Case`

- **Readable and connected data responses**  
  Displays descriptive names instead of raw IDs.  
  Example: shows `"Kilowatt Case"` instead of `"crate-4904"`.

- **Search result export to XML**  
  Any filtered result can be exported as XML via:  
  `/skins/xml?crateName=Kilowatt Case`

- **Authentication-protected section using Basic Auth**  
  The `Keys` section is secured using Basic Auth with

- **Search caching for performance optimization**  
  Frequently repeated queries are cached to improve response times and reduce database load.

- **Database versioning with Flyway migrations**  
  Database schema and optional seed data are automatically managed at startup to ensure consistency across environments.

- **Fully containerized with Docker & Docker Compose**  
  The project can be launched with a single command:
  down with:
  ```bash
     docker-compose down -v
  ```
  up with:
  ```bash
   docker-compose up --build
  ```

---


## **Project Structure**

El proyecto está organizado según una arquitectura limpia, con las siguientes carpetas principales:
- `api/v1`: Contains the services, utilities, and route definitions.
- `api/v1/requests/`: JSON serialization for incoming request payloads.
- `api/v1/responses/`: JSON serialization for API response payloads.
- `api/v1/routes/`: Exposed API routes.
- `api/v1/services/`: Business logic related to the API.
- `api/v1/utils/`: Utility functions used across the API.
- `database/`: Modules responsible for interacting with the database.
- `database/migrations/`: Class that handles table structure migrations using Flyway.
- `database/models/`: Database entities and table schemas (managed via Exposed).
- `database/repositories/`: Interfaces that define the data source access.
- `database/repositories/impl`: Implementations of the repository interfaces, responsible for resolving and dispatching data.
- `database/seeder`: Contains the logic for bulk inserting data fetched from the external API.
- `resources/db/migration`: Contains the logic for migration DB SQL.


## **API Routes**

## 🎯 Skins 
- **Route: GET /api/v1/skins**
- **Route: GET /api/v1/skins/xml**
 
get all stkins.


**Query Parameters**: 
- id (String, opcional)
- name (String, opcional)
- description (String, opcional)
- image (String, opcional)
- crateName (String, opcional)
- teamName (String, opcional)


**Response**:

```json
[
 {
        "id": "skin-0063b0378b5b",
        "name": "★ Butterfly Knife | Ultraviolet",
        "description": "This is a custom-designed balisong, commonly known as a butterfly knife. The defining characteristic of this weapon is the fan-like opening of a freely pivoting blade, allowing rapid deployment or concealment. As a result, butterfly knives are outlawed in many countries. It has individual parts spray-painted solid colors in a black and purple color scheme.\\n\\n<i>Elegant design paired with brutal intent</i>",
        "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/default_generated/weapon_knife_butterfly_so_purple_light_png.png",
        "team": {
            "id": "both",
            "name": "Both Teams"
        },
        "crates": [
            {
                "id": "crate-4351",
                "name": "Spectrum Case",
                "description": null,
                "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_community_16_png.png"
            }
        ]
    },
    // Others...
]
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<skins>
    <skin>
        <id>skin-0063b0378b5b</id>
        <name>★ Butterfly Knife | Ultraviolet</name>
        <description>This is a custom-designed balisong, commonly known as a butterfly knife. The defining characteristic of this weapon is the fan-like opening of a freely pivoting blade, allowing rapid deployment or concealment. As a result, butterfly knives are outlawed in many countries. It has individual parts spray-painted solid colors in a black and purple color scheme.\n\n&lt;i&gt;Elegant design paired with brutal intent&lt;/i&gt;</description>
        <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/default_generated/weapon_knife_butterfly_so_purple_light_png.png</image>
        <team>
            <id>both</id>
            <name>Both Teams</name>
        </team>
        <crates>
            <crate>
                <id>crate-4351</id>
                <name>Spectrum Case</name>
                <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_community_16_png.png</image>
            </crate>
        </crates>
    </skin>
    // Others...
</skins>
```

## 👥 Agents  

- **Route: GET /api/v1/agents**
- **Route: GET /api/v1/agents/xml**
 
get all agents.


**Query Parameters**: 
- id (String, opcional)
- name (String, opcional)
- description (String, opcional)
- image (String, opcional)
- teamName (String, opcional)


**Response**:

```json
[
   {
        "id": "agent-4757",
        "name": "Cmdr. Davida 'Goggles' Fernandez | SEAL Frogman",
        "description": "Possessing an exquisite mammalian dive reflex, Cmdr. Fernandez is able to slow her heart rate to undetectable levels and navigate darkness, ice, and deep sea defense systems. She excels in her human aquatic potential and her skill as an elite marksman and soldier. 'Goggles' earned her nickname when a bullet struck her left eye—or would have—if it weren't for 'Wet Sox' reminding her to grab her full kit 'for the last time!' Despite her occasional forgetfulness, Cmdr. Fernandez is one of two elite combat diver SEALs to be awarded The Navy Cross for classified underwater operations. \\n\\n<i>Be still my beating heart.</i>",
        "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/characters/customplayer_ctm_diver_varianta_png.png",
        "team": {
            "id": "counter-terrorists",
            "name": "Counter-Terrorist"
        }
    },
 
    // Others...
]
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<agents>
    <agent>
        <id>agent-4757</id>
        <name>Cmdr. Davida &apos;Goggles&apos; Fernandez | SEAL Frogman</name>
        <description>Possessing an exquisite mammalian dive reflex, Cmdr. Fernandez is able to slow her heart rate to undetectable levels and navigate darkness, ice, and deep sea defense systems. She excels in her human aquatic potential and her skill as an elite marksman and soldier. &apos;Goggles&apos; earned her nickname when a bullet struck her left eye—or would have—if it weren&apos;t for &apos;Wet Sox&apos; reminding her to grab her full kit &apos;for the last time!&apos; Despite her occasional forgetfulness, Cmdr. Fernandez is one of two elite combat diver SEALs to be awarded The Navy Cross for classified underwater operations. \n\n&lt;i&gt;Be still my beating heart.&lt;/i&gt;</description>
        <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/characters/customplayer_ctm_diver_varianta_png.png</image>
        <team>
            <id>counter-terrorists</id>
            <name>Counter-Terrorist</name>
        </team>
    </agent>
    // Others...
</agents>
```

## 📦 Crates  

- **Route: GET /api/v1/crates**
- **Route: GET /api/v1/crates/xml**
 
get all crates.

**Query Parameters**: 
- id (String, opcional)
- name (String, opcional)
- description (String, opcional)
- image (String, opcional)

**Response**:

```json
[
    {
        "id": "crate-1210",
        "name": "Gift Package",
        "description": "When used, a random player in your match will get a random item as a gift from you.",
        "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/gift1player_png.png"
    },
    // Others...
]
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<crates>
    <crate>
        <id>crate-1210</id>
        <name>Gift Package</name>
        <description>When used, a random player in your match will get a random item as a gift from you.</description>
        <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/gift1player_png.png</image>
    </crate>
    // Others...
</crates>
```

## **Autenticación**
For routes that require authentication, Basic Auth has been implemented.

### **Rutas protegidas**:

## 🔑 Keys   

- **POST /api/v1/keys**
- **POST /api/v1/keys/xml**

These routes require an `Authorization` of type `Basic Auth` with the following credentials.
  username: 
  ```bash
  admin
  ```
  password: 
  ```bash
  secret
  ```

get all keys.


**Query Parameters**: 
- id (String, opcional)
- name (String, opcional)
- description (String, opcional)
- image (String, opcional)
- crateName (String, opcional)


**Response**:

```json
[
     {
        "id": "key-1204",
        "name": "eSports Key",
        "description": "This key opens any eSports Case.\\n\\nA portion of the proceeds from the sale of this key will go towards one of the established community-run CS:GO tournaments. Stay tuned for further announcements.",
        "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/tools/weapon_case_key_special_1_png.png",
        "crates": [
            {
                "id": "crate-4019",
                "name": "eSports 2014 Summer Case",
                "description": "A portion of the proceeds from the key used to unlock this will help support CS:GO professional tournament prize pools.",
                "image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_esports_2014_summer_png.png"
            }
        ]
    },
    // Others...
]
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<keys>
    <key>
        <id>key-1204</id>
        <name>eSports Key</name>
        <description>This key opens any eSports Case.\n\nA portion of the proceeds from the sale of this key will go towards one of the established community-run CS:GO tournaments. Stay tuned for further announcements.</description>
        <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/tools/weapon_case_key_special_1_png.png</image>
        <crate>
            <id>crate-4019</id>
            <name>eSports 2014 Summer Case</name>
            <description>A portion of the proceeds from the key used to unlock this will help support CS:GO professional tournament prize pools.</description>
            <image>https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_esports_2014_summer_png.png</image>
        </crate>
    </key>
    // Others...
</keys>
```
**Example Auth**:

```bash
  curl -X GET http://localhost:8080/api/v1/keys -H "Authorization: Basic $(echo -n 'admin:secret' | base64)"
```

---











