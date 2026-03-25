# Serenity BDD Test Generation Skill

Skill especializado para generar y mantener tests con Serenity BDD, Screenplay y Cucumber.

**Propósito:** Automatizar la creación de Tasks, Questions, Steps y Feature files basados en requisitos.

## Uso

```bash
# Generar nuevos tests basados en descripción
claude-skills run serenity-bdd-test-gen --description "API para crear usuarios" --resource "users" --action "POST"

# Validar sintaxis de features
claude-skills run serenity-bdd-test-gen --validate features/usuarios.feature

# Generar modelo de datos
claude-skills run serenity-bdd-test-gen --generate-model User
```

## Funcionalidades

- ✅ Generar Tasks automáticamente
- ✅ Generar Questions para validaciones
- ✅ Crear Feature files (.feature) en español
- ✅ Generar Step Definitions
- ✅ Validar sintaxis Gherkin
- ✅ Generar modelos de datos (POJOs)
