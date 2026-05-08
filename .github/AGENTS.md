# Copilot Instructions — Serenity BDD + Screenplay

Reglas y convenciones para desarrollar casos de prueba en este proyecto.

---

## Stack Tecnológico

- **Java 11** — versión obligatoria del compilador
- **Maven** — único gestor de builds permitido (no Gradle)
- **Serenity BDD 3.9.0** — framework de reportes y orquestación
- **Cucumber 7.14.0** — definición de escenarios en Gherkin
- **REST Assured 5.3.2** — cliente HTTP para pruebas de API
- **Jackson** — serialización/deserialización de JSON con `@JsonProperty`
- **JUnit 4** — runner de tests

---

## Patrón Screenplay (obligatorio)

Todos los tests deben seguir estrictamente el patrón Screenplay con estas 4 capas:

### 1. Actors (`screenplay/actors/`)
- Representan al usuario que interactúa con la API
- Siempre inicializar con `CallAnApi.at(ApiEndpoints.BASE_URL)`
- Usar `OnStage.theActorCalled("nombre")` en el step `@Dado`

```java
OnStage.theActorCalled("Usuario1").can(CallAnApi.at(ApiEndpoints.BASE_URL));
```

### 2. Tasks (`screenplay/tasks/`)
- Encapsulan acciones de negocio (GET, POST, PUT, DELETE)
- Deben implementar la interfaz `Task`
- Siempre proveer un método estático de fábrica en español
- Usar las interacciones de Serenity: `Get`, `Post`, `Put`, `Delete`

```java
public class ObtenerPublicaciones implements Task {
    public static ObtenerPublicaciones del(Integer userId) { ... }
}
```

### 3. Questions (`screenplay/questions/`)
- Encapsulan validaciones sobre el estado del sistema
- Deben implementar `Question<T>` con el tipo de retorno correcto
- Siempre proveer un método estático de fábrica en español

```java
public class ObtenerCodigoDeRespuesta implements Question<Integer> {
    public static ObtenerCodigoDeRespuesta delServidor() { ... }
}
```

### 4. Interactions (`screenplay/interactions/`)
- Interacciones HTTP de bajo nivel reutilizables
- Solo usar cuando la lógica no cabe en una Task

---

## Estructura de Carpetas

```
src/test/java/com/projectaicopilot/
├── screenplay/
│   ├── actors/           # ApiTester
│   ├── tasks/            # Una clase por acción de negocio
│   ├── interactions/     # Helpers HTTP reutilizables
│   ├── questions/        # Una clase por validación
│   ├── models/           # POJOs con @JsonProperty
│   ├── userinterfaces/   # Endpoints y rutas
│   ├── utils/            # ApiEndpoints, RestConfig
│   └── enums/            # StatusCode, HttpMethods
├── stepdefinitions/      # Cucumber steps en español
└── runners/              # CucumberRunner

src/test/resources/features/
└── *.feature             # Gherkin en español
```

---

## Reglas de Nomenclatura

### Clases
- **Tasks**: PascalCase descriptivo en español → `ObtenerPublicaciones`, `CrearPublicacion`, `EliminarUsuario`
- **Questions**: PascalCase descriptivo en español → `ObtenerCodigoDeRespuesta`, `ValidarPublicacionCreada`
- **Models**: PascalCase en inglés o español → `Post`, `User`
- **Enums**: PascalCase → `StatusCode`, `HttpMethods`

### Métodos de fábrica
- Siempre en español y semánticos:
  - `del(userId)`, `conId(id)`, `con(post)`, `delServidor()`

### Step Definitions
- Siempre en español, claros y reutilizables
- Un método por step, sin lógica de negocio dentro del step

---

## Feature Files (Gherkin)

- **Idioma**: siempre `# language: es`
- **Keywords**: `Dado`, `Cuando`, `Entonces`, `Y`, `Pero`
- **Tags obligatorios**: cada `Característica` y cada `Escenario` debe tener al menos un `@tag`
- **Estructura de tags**: `@recurso @recurso @accion` (ej: `@usuarios @eliminar-usuario`)

```gherkin
# language: es
@usuarios
Característica: Gestión de Usuarios

  @usuarios @eliminar-usuario
  Escenario: Eliminar un usuario
    Dado que el usuario desea eliminar un usuario
    Cuando elimino el usuario con id 1
    Entonces la respuesta del servidor debe tener estado OK
```

---

## Modelos (POJOs)

- Siempre usar `@JsonProperty("nombreCampoJSON")` en cada campo
- Constructor vacío obligatorio para deserialización
- Getters y Setters para todos los campos
- Sobrescribir `toString()` para facilitar depuración

```java
public class Post {
    @JsonProperty("userId")
    private Integer userId;

    public Post() {}
    // getters, setters, toString
}
```

---

## Enums

### StatusCode — SIEMPRE usar en validaciones de status HTTP
```java
// ✅ Correcto
equalTo(StatusCode.OK.getCode())

// ❌ Incorrecto
equalTo(200)
```

### HttpMethods — disponible para futuro uso (abstractor de interacciones)

---

## Step Definitions

- Cada Step Definition file corresponde a un recurso (Publicaciones, Usuarios, etc.)
- El hook `@Before` en `CucumberHooks` inicializa el stage automáticamente
- Usar `OnStage.theActorInTheSpotlight()` para continuar el actor del step anterior
- Usar `seeThat()` para todas las validaciones

```java
@Entonces("la respuesta del servidor debe tener estado OK")
public void validarCodigoRespuestaOK() {
    OnStage.theActorInTheSpotlight().should(
        seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(StatusCode.OK.getCode()))
    );
}
```

---

## API Base

- **URL**: `https://jsonplaceholder.typicode.com`
- **Endpoints disponibles**: `/posts`, `/users`, `/comments`, `/albums`, `/todos`
- Siempre usar `ApiEndpoints.*` para las rutas, nunca strings hardcodeados

```java
// ✅ Correcto
Get.resource(ApiEndpoints.getUserById(userId))

// ❌ Incorrecto
Get.resource("/users/" + userId)
```

---

## CucumberRunner

- Siempre usar tags de los features para filtrar ejecución
- Tags actuales: `"@publicaciones or @usuarios"`
- Para correr un subconjunto: `"@publicaciones and @crear"`

---

## Comandos Útiles

```bash
# Compilar
mvn clean compile

# Ejecutar todos los tests
mvn clean test

# Ejecutar solo un tag
mvn clean test -Dcucumber.filter.tags="@usuarios"

# Ver reporte Serenity
open target/site/serenity/index.html
```

---

## Checklist para Nuevos Tests

Antes de hacer commit, verificar:

- [ ] Feature file en español con `# language: es` y tags correctos
- [ ] Task creada en `screenplay/tasks/` con método estático en español
- [ ] Question creada en `screenplay/questions/` si hay nueva validación
- [ ] Step Definitions registrados en el archivo correspondiente
- [ ] Modelos con `@JsonProperty` si se agrega nuevo POJO
- [ ] Usando `StatusCode` enum en lugar de números
- [ ] Tests pasan: `mvn clean test` → `Tests run: N, Failures: 0`
- [ ] Commit con mensaje descriptivo

---

## Rol del Agente

Cuando trabajes en este proyecto, asume el rol de **especialista en automatización con Serenity BDD y Screenplay RestAssured**.

### Responsabilidades:
- ✅ Generar Tasks, Questions e Interactions siguiendo el patrón Screenplay
- ✅ Crear Feature files en español con Gherkin
- ✅ Escribir Step Definitions limpios y mantenibles
- ✅ Validar estructura y sintaxis de tests
- ✅ Generar modelos de datos (POJOs) con Jackson annotations
- ✅ Ejecutar tests y generar reportes
- ✅ Resolver fallos de compilación y ejecución

### Tools Disponibles:
- **Terminal**: Compilar, ejecutar tests, generar reportes
- **File System**: Crear/editar Tasks, Questions, Features, Models
- **Git**: Versionado de cambios
