---
name: contract-testing
description: "Verificar y reparar contratos de API en el proyecto Serenity BDD. Usar cuando: el equipo de desarrollo cambia el swagger/openapi.yaml y necesitas detectar qué tests se rompen; cuando quieres verificar cobertura de contratos; cuando necesitas agregar un nuevo contrato para un endpoint; cuando hay diferencias entre el openapi.yaml y los schemas JSON o los modelos Java. Keywords: contrato, schema, openapi, swagger, drift, breaking change, JSON Schema, verificar contrato, reparar tests."
argument-hint: "Opcional: endpoint específico a verificar, ej: posts o users"
---

# Contract Testing — Drift Detection & Auto-Repair

## Descripción

Esta skill verifica que los tests automatizados de Serenity BDD estén alineados con el contrato definido en `openapi.yaml` (fuente de verdad). Cuando el equipo de desarrollo hace cambios al spec, detecta el drift y ofrece reparar automáticamente los archivos afectados.

## Flujo Obligatorio (SIEMPRE en este orden)

```
Paso 1: Detectar drift → Paso 2: Reportar y pedir autorización → Paso 3: Reparar (solo si aprobado)
```

**NUNCA modificar archivos sin completar el Paso 2 primero.**

---

## Paso 1 — Detectar Drift

### 1.1 Leer la fuente de verdad
- Leer `src/test/resources/openapi.yaml`
- Extraer los schemas de cada componente: `Post`, `User`, etc.
- Extraer los endpoints con sus operaciones y respuestas esperadas

### 1.2 Comparar schemas JSON
Para cada schema en `openapi.yaml`, comparar contra el archivo correspondiente en `src/test/resources/schemas/`:

| openapi.yaml schema | Archivo schema |
|---|---|
| `components/schemas/Post` | `schemas/post-schema.json` |
| `components/schemas/User` | `schemas/user-schema.json` |
| Array de `Post` (GET /posts) | `schemas/posts-list-schema.json` |

**Diferencias a detectar:**
- Campo nuevo en openapi.yaml que no existe en el schema JSON
- Campo eliminado del openapi.yaml que aún está en el schema JSON
- Tipo de campo cambiado (ej: `string` → `integer`)
- Campo pasó de opcional a `required` o viceversa

### 1.3 Comparar modelos Java
Para cada schema en `openapi.yaml`, comparar contra el modelo Java correspondiente en `src/test/java/com/projectaicopilot/screenplay/models/`:

| Schema | Modelo Java |
|---|---|
| `Post` | `Post.java` |
| `User` | `User.java` |

**Diferencias a detectar:**
- Campo en openapi.yaml sin `@JsonProperty` correspondiente en el modelo Java
- Campo en el modelo Java sin correspondencia en openapi.yaml

### 1.4 Verificar cobertura de escenarios @contrato
Listar todos los endpoints en `openapi.yaml` con operaciones GET/POST/PUT/DELETE.
Comparar contra escenarios con tag `@contrato` en los feature files:
- `src/test/resources/features/publicaciones.feature`
- `src/test/resources/features/usuarios.feature`

**Endpoints cubiertos actualmente:**
- ✅ GET /posts?userId={id} — `@publicaciones @contrato` "Validar contrato de listado de publicaciones"
- ✅ GET /posts/{id} — `@publicaciones @contrato` "Validar contrato de publicación por id"
- ✅ POST /posts — `@publicaciones @contrato` "Validar contrato de publicación creada"
- ✅ GET /users/{id} — `@usuarios @contrato` "Validar contrato de usuario por id"
- ❌ PUT /posts/{id} — sin cobertura de contrato
- ❌ DELETE /posts/{id} — sin cobertura de contrato
- ❌ DELETE /users/{id} — sin cobertura de contrato

---

## Paso 2 — Reportar y Pedir Autorización

Antes de tocar cualquier archivo, mostrar al usuario:

```
=== REPORTE DE DRIFT DETECTADO ===

Cambios en openapi.yaml vs estado actual:

📋 SCHEMAS JSON (src/test/resources/schemas/):
  - post-schema.json: [listar diferencias encontradas]
  - user-schema.json: [listar diferencias encontradas]

☕ MODELOS JAVA:
  - Post.java: [listar campos a agregar/eliminar]
  - User.java: [listar campos a agregar/eliminar]

📝 ESCENARIOS @contrato:
  - [listar escenarios a agregar/modificar]

🔧 ARCHIVOS QUE SE MODIFICARÍAN:
  1. src/test/resources/schemas/post-schema.json
  2. src/test/java/com/projectaicopilot/screenplay/models/Post.java
  3. (etc.)

¿Deseas que aplique estas reparaciones? (responde sí/no)
```

**Si no hay drift:** informar "No se detectaron diferencias. Los contratos están alineados con openapi.yaml."

**Esperar respuesta del usuario antes de continuar.**

---

## Paso 3 — Reparar (Solo si el usuario aprobó)

### 3.1 Actualizar schemas JSON
Editar los archivos `schemas/*.json` para reflejar exactamente los campos y tipos del `openapi.yaml`.

Estructura base de un schema JSON:
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "required": ["campo1", "campo2"],
  "properties": {
    "campo1": { "type": "integer" },
    "campo2": { "type": "string" }
  }
}
```

### 3.2 Actualizar modelos Java
Para cada campo nuevo en openapi.yaml, agregar en el modelo Java:
```java
@JsonProperty("nombreCampoJSON")
private TipoCampo nombreCampo;
```
También agregar getter, setter y actualizar `toString()`.

### 3.3 Actualizar/agregar escenarios @contrato
Para cada endpoint nuevo o modificado en openapi.yaml, agregar en el feature file correspondiente:
```gherkin
@recurso @contrato
Escenario: Validar contrato de [descripción]
  Dado que el usuario desea verificar el contrato de [recurso]
  Cuando [acción sobre el endpoint]
  Entonces el contrato de la respuesta debe coincidir con el esquema de [recurso]
```

### 3.4 Confirmar que los tests pasan
Ejecutar en terminal:
```bash
mvn clean test -Dcucumber.filter.tags="@contrato"
```
Verificar que todos los escenarios `@contrato` pasan. Si hay fallos, reportarlos al usuario.

---

## Cómo agregar un contrato para un endpoint nuevo

Cuando se agrega un endpoint nuevo en `openapi.yaml` sin cobertura de contrato:

1. **Crear schema JSON** en `src/test/resources/schemas/<recurso>-schema.json`
2. **Verificar/actualizar modelo Java** en `src/test/java/com/projectaicopilot/screenplay/models/<Recurso>.java`
3. **Agregar escenario** con tag `@contrato` en el feature file correspondiente
4. **Agregar step** `@Entonces("el contrato de la respuesta debe coincidir con el esquema de <recurso>")` si no existe
5. **Verificar** con `mvn clean test -Dcucumber.filter.tags="@contrato"`

---

## Cómo simular un breaking change

Para probar que la skill detecta un cambio en el backend:

1. Editar `src/test/resources/openapi.yaml`
2. Agregar un campo nuevo a un schema existente, ej en `Post`:
   ```yaml
   subtitle:
     type: string
     description: Subtítulo de la publicación
   ```
3. Invocar `/contract-testing` en el chat
4. La skill detectará que `post-schema.json` y `Post.java` están desactualizados
5. Aprobará los cambios y los aplicará automáticamente

---

## Referencia de archivos clave

| Archivo | Propósito |
|---|---|
| `src/test/resources/openapi.yaml` | Fuente de verdad — contratos de la API |
| `src/test/resources/schemas/post-schema.json` | JSON Schema de un Post |
| `src/test/resources/schemas/posts-list-schema.json` | JSON Schema de lista de Posts |
| `src/test/resources/schemas/user-schema.json` | JSON Schema de un User |
| `src/test/java/.../questions/ValidarEsquemaDeRespuesta.java` | Question de Serenity que valida el schema |
| `src/test/java/.../models/Post.java` | Modelo Java de Post con @JsonProperty |
| `src/test/java/.../models/User.java` | Modelo Java de User con @JsonProperty |
| `src/test/resources/features/publicaciones.feature` | Escenarios @contrato de publicaciones |
| `src/test/resources/features/usuarios.feature` | Escenarios @contrato de usuarios |
