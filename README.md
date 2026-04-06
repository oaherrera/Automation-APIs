# Project AI Copilot - Serenity BDD Automation

## 📋 Descripción

Repositorio profesional de **automatización de APIs con Serenity BDD**, utilizando el **patrón Screenplay**, **Cucumber Gherkin** y **JSONPlaceholder** como API de ejemplo.

**Stack:**
- Java 11
- Maven
- Serenity BDD 4.0.1
- Cucumber 7.14.0
- REST Assured 5.3.2
- JUnit 4

---

## 🏗️ Estructura del Proyecto

```
projectAICopilot/
├── pom.xml                                       # Configuración Maven
├── src/test/
│   ├── java/com/projectaicopilot/
│   │   ├── screenplay/
│   │   │   ├── actors/                          # Definición de actores
│   │   │   │   └── ApiTester.java
│   │   │   ├── tasks/                           # Tareas del patrón Screenplay
│   │   │   │   ├── ObtenerPublicaciones.java
│   │   │   │   ├── ObtenerPublicacionPorId.java
│   │   │   │   ├── CrearPublicacion.java
│   │   │   │   ├── ObtenerUsuarioPorId.java
│   │   │   │   └── EliminarUsuario.java
│   │   │   ├── interactions/                    # Interacciones con APIs
│   │   │   │   └── ApiInteractions.java
│   │   │   ├── questions/                       # Validaciones y preguntas
│   │   │   │   ├── ValidarPublicacionCreada.java
│   │   │   │   └── ObtenerCodigoDeRespuesta.java
│   │   │   ├── models/                          # Modelos de datos
│   │   │   │   ├── Post.java
│   │   │   │   └── User.java
│   │   │   ├── userinterfaces/                  # Interfaces de usuario (endpoints)
│   │   │   ├── utils/                           # Utilidades
│   │   │   │   ├── ApiEndpoints.java
│   │   │   │   └── RestConfig.java
│   │   │   └── enums/                           # Enumeraciones
│   │   │       ├── HttpMethods.java
│   │   │       └── StatusCode.java
│   │   ├── stepdefinitions/                     # Definiciones de pasos Cucumber
│   │   │   ├── PublicacionesSteps.java
│   │   │   └── UsuariosSteps.java
│   │   └── runners/
│   │       └── CucumberRunner.java              # Test Runner
│   └── resources/
│       └── features/                             # Feature files en español
│           ├── publicaciones.feature
│           └── usuarios.feature
├── .github/
│   ├── copilot-instructions.md                  # Instrucciones para Copilot
│   ├── skills/                                  # Skills personalizados
│   │   └── test-runner.md
│   └── agents/                                  # Agentes personalizados
├── target/                                      # Compilados
└── README.md                                    # Este archivo
```

---

## 🚀 Quick Start

### 1. Clonar o preparar el proyecto

```bash
cd /Users/oscar.arbey.herrera/Documents/projectAIClaude/projectAICopilot
```

### 2. Compilar

```bash
mvn clean compile
```

### 3. Ejecutar tests

```bash
mvn test
```

### 4. Ver reportes

Los reportes se generan en:
```
target/cucumber-reports/cucumber.html
target/site/serenity/index.html
```

---

## 📝 Patrón Screenplay

El patrón Screenplay organiza los tests en 4 capas:

### 1. **Actors** 🎭
Representan a lo usuarios que interactúan con el sistema.
```java
Actor actor = ApiTester.queInteractuaConAPIs("Usuario1")
    .whoCan(CallAnApi.at(ApiEndpoints.BASE_URL));
```

### 2. **Tasks** ✅
Acciones que el actor realiza. Encapsulan la lógica de negocio.
```java
actor.attemptsTo(
    ObtenerPublicaciones.del(1)
);
```

### 3. **Interactions** 🔗
Interacciones de bajo nivel con la API (GET, POST, PUT, DELETE).
```java
Get.to(ApiEndpoints.POSTS)
```

### 4. **Questions** ❓
Validaciones y aserciones sobre el estado del sistema.
```java
actor.should(
    seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(200))
);
```

---

## 🔧 Ejemplos de Uso

### Obtener publicaciones de un usuario

**Feature (Gherkin):**
```gherkin
Escenario: Obtener todas las publicaciones de un usuario
  Cuando solicito las publicaciones del usuario con id 1
  Entonces la respuesta del servidor debe tener estado 200
```

**Implementación:**
```java
@Cuando("solicito las publicaciones del usuario con id {int}")
public void solicitoPublicacionesDelUsuario(Integer userId) {
    actor.attemptsTo(
        ObtenerPublicaciones.del(userId)
    );
}
```

---

## 🌐 APIs Disponibles (JSONPlaceholder)

- `GET /posts` - Obtener todas las publicaciones
- `GET /posts/{id}` - Obtener publicación por ID
- `POST /posts` - Crear nueva publicación
- `GET /users/{id}` - Obtener usuario por ID
- `GET /comments` - Obtener comentarios
- `GET /albums` - Obtener álbumes
- `GET /todos` - Obtener TODOs

Base URL: `https://jsonplaceholder.typicode.com`

---

## 📚 Recursos Adicionales

- [Serenity BDD Docs](https://serenity-bdd.info/)
- [Cucumber Docs](https://cucumber.io/docs/cucumber/)
- [Screenplay Pattern Guide](https://serenity-bdd.info/docs/screenplay/screenplay_fundamentals)
- [REST Assured](https://rest-assured.io/)

---

## 👨‍💻 Contribuir

1. Crea una rama: `git checkout -b feature/nueva-feature`
2. Haz commits descriptivos
3. Push a la rama: `git push origin feature/nueva-feature`
4. Abre un Pull Request

---

## 📞 Soporte

Para dudas o problemas:
- Revisa la documentación de Serenity BDD
- Consulta los ejemplos en `src/test/resources/features/`
- Verifica la estructura de tasks en `src/test/java/com/projectaicopilot/screenplay/tasks/`

---

**Creado con ❤️ usando Serenity BDD + Screenplay Pattern**
