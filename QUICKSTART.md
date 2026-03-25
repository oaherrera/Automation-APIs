# 🚀 Project AI Copilot - Guía de Inicio Rápido

¡Tu repositorio de **Serenity BDD con Screenplay en español** está listo! 🎉

---

## 📍 Ubicación

```
/Users/oscar.arbey.herrera/Documents/projectAIClaude/projectAICopilot
```

---

## ✅ Estado Actual

- ✅ **Maven** configurado (Java 11)
- ✅ **Serenity BDD 4.0.1** integrado
- ✅ **Cucumber 7.14.0** con Gherkin en español
- ✅ **Patrón Screenplay** implementado
- ✅ **JSONPlaceholder** como API base
- ✅ **Compilación exitosa** (sin errores)
- ✅ **Skills y Agentes** personalizados

---

## 💻 Primeros Pasos

### 1. Navegar al proyecto

```bash
cd /Users/oscar.arbey.herrera/Documents/projectAIClaude/projectAICopilot
```

### 2. Compilar el proyecto

```bash
mvn clean compile
```

### 3. Ejecutar los tests (cuando estén listos)

```bash
mvn test
```

### 4. Ver reportes

- **Cucumber Report:** `target/cucumber-reports/cucumber.html`
- **Serenity Report:** `target/site/serenity/index.html`

---

## 📁 Estructura del Proyecto

```
projectAICopilot/
├── pom.xml                                    # Configuración Maven ⚙️
├── README.md                                  # Documentación completa
├── .instructions.md                           # Instrucciones para Claude
├── .gitignore
├── serenity.properties
│
├── src/test/
│   ├── java/com/projectaicopilot/
│   │   ├── screenplay/
│   │   │   ├── actors/                       # 🎭 Actores (ApiTester)
│   │   │   ├── tasks/                        # ✅ Tareas (Obtener, Crear, Updatear)
│   │   │   ├── interactions/                 # 🔗 Interacciones REST
│   │   │   ├── questions/                    # ❓ Validaciones
│   │   │   ├── models/                       # 📦 Modelos de datos (Post, User)
│   │   │   ├── userinterfaces/               # 🌐 Endpoints
│   │   │   ├── utils/                        # 🛠️ Utilidades (ApiEndpoints, RestConfig)
│   │   │   └── enums/                        # 📋 Enumeraciones
│   │   ├── stepdefinitions/                  # 📝 Step Definitions en Español
│   │   │   ├── PublicacionesSteps.java
│   │   │   └── UsuariosSteps.java
│   │   └── runners/
│   │       └── CucumberRunner.java           # Test Runner
│   └── resources/
│       └── features/                          # 🎯 Feature files (.feature)
│           ├── publicaciones.feature
│           └── usuarios.feature
│
├── .claude/
│   ├── skills/
│   │   └── serenity-bdd-test-gen/            # Skill para generar tests
│   │       └── SKILL.md
│   ├── agents/
│   │   └── test-runner.md                    # Agent para ejecutar tests
│   └── config.sh                             # Configuración personalizada
```

---

## 🎯 Archivos Clave

| Archivo | Propósito |
|---------|-----------|
| `pom.xml` | Dependencias Maven y plugins |
| `README.md` | Documentación completa del proyecto |
| `.instructions.md` | Instrucciones para Claude cuando trabaje aquí |
| `serenity.properties` | Configuración de Serenity BDD |
| `src/test/resources/features/*.feature` | Escenarios en Gherkin (español) |
| `src/test/java/.../screenplay/` | Implementación del patrón Screenplay |
| `.claude/skills/` | Skills personalizados para este proyecto |
| `.claude/agents/` | Agentes personalizados para este proyecto |

---

## 🧪 Features Disponibles

Ya hay **2 feature files de ejemplo** listos:

### 1. `publicaciones.feature` 📝
```gherkin
Escenario: Obtener todas las publicaciones de un usuario
Escenario: Obtener una publicación específica por su id
Escenario: Crear una nueva publicación
Escenario: Validar estructura de respuesta
```

### 2. `usuarios.feature` 👥
```gherkin
Escenario: Obtener información de un usuario
Escenario: Validar que el email existe en la respuesta
```

---

## 📚 Patrón Screenplay (Implementado)

El proyecto ya tiene la estructura lista para escribir tests en patrón Screenplay:

**Ejemplo de uso:**
```java
Actor actor = ApiTester.queInteractuaConAPIs("Usuario1")
    .whoCan(CallAnApi.at(ApiEndpoints.BASE_URL));

actor.attemptsTo(
    ObtenerPublicaciones.del(1)
);

actor.should(
    seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(200))
);
```

---

## 🔧 Siguientes Pasos

### Opción 1: Completar los Step Definitions
Los archivos `PublicacionesSteps.java` y `UsuariosSteps.java` ya tienen estructura base. Completa las validaciones faltantes.

### Opción 2: Crear nuevos Tests
1. Crea un nuevo `.feature` en `src/test/resources/features/`
2. Crea una nueva `Task` en `src/test/java/.../screenplay/tasks/`
3. Crea un nuevo `StepDefinitions.java` en `src/test/java/.../stepdefinitions/`

### Opción 3: Usar el Skill Personalizado
El skill `serenity-bdd-test-gen` puede generar tests automáticamente:
```bash
claude-skills run serenity-bdd-test-gen --description "Obtener álbumes" --resource "albums" --action "GET"
```

---

## 📦 APIs Disponibles (JSONPlaceholder)

- `GET /posts` → Publicaciones
- `GET /posts/{id}` → Publicación específica
- `POST /posts` → Crear publicación
- `GET /users/{id}` → Información del usuario
- `GET /comments` → Comentarios
- `GET /albums` → Álbumes
- `GET /todos` → TODOs

**Base URL:** `https://jsonplaceholder.typicode.com`

---

## 🎨 Configuración Personalizada

Este proyecto tiene instrucciones personalizadas para Claude en `.instructions.md`. Cuando trabajes aquí, Claude asume el rol de:

✅ **Especialista en Serenity BDD + Screenplay**
- Crea Tasks, Questions e Interactions
- Escribe Feature files en español
- Completa Step Definitions
- Ejecuta tests y reportes
- Resuelve problemas de compilación

---

## 🚨 Tips Importantes

1. **Siempre en español:** Feature files, nombres de variables, comentarios
2. **Patrón Screenplay:** Usa Tasks para acciones, Questions para validaciones
3. **Modularidad:** Reutiliza Tasks y Questions en múltiples scenarios
4. **Jackson:** Usa `@JsonProperty` en los modelos para serialización
5. **REST Assured:** Ya está integrado con Serenity BDD

---

## 📞 Troubleshooting

| Problema | Solución |
|----------|----------|
| `mvn: command not found` | Instala Maven: `brew install maven` |
| Compilación lenta | Primera ejecución descarga dependencias. Espera. |
| Tests no se ejecutan | Verifica que el `CucumberRunner.java` exista |
| Features no encontradas | Asegúrate de que `.feature` esté en `src/test/resources/features/` |

---

## 📖 Documentación

- [README.md](./README.md) - Documentación completa del proyecto
- [serenity.properties](./serenity.properties) - Configuración de Serenity
- [pom.xml](./pom.xml) - Dependencias y plugins Maven
- [.instructions.md](./.instructions.md) - Instrucciones personalizadas para Claude

---

## ✨ ¡Listo para empezar!

Ahora puedes:
1. ✅ Compilar: `mvn clean compile`
2. ✅ Crear nuevos tests en patrón Screenplay
3. ✅ Escribir scenarios en Gherkin (español)
4. ✅ Ejecutar: `mvn test`
5. ✅ Ver reportes: `target/site/serenity/index.html`

¿Necesitas ayuda creando un test específico? Pregúntale a Claude. 🤖

---

**Creado:** 2026-03-24  
**Stack:** Java 11 + Maven + Serenity BDD + Screenplay + Cucumber  
**Estado:** ✅ Listo para usar
