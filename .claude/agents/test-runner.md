# Test Runner Agent

Agente especializado en ejecutar tests de Serenity BDD y generar reportes.

**Responsabilidades:**
- Compilar el proyecto Maven
- Ejecutar tests específicos o suites completas
- Generar reportes HTML
- Validar resultados y fallos
- Limpieza de artifacts

## Uso

```bash
# Ejecutar todos los tests
claude-agent run test-runner --command execute-all

# Ejecutar feature específico
claude-agent run test-runner --feature publicaciones.feature

# Generar reporte
claude-agent run test-runner --command generate-report

# Debug mode
claude-agent run test-runner --debug --feature usuarios.feature
```

## Salida

El agente devuelve:
- Status de compilación y ejecución
- Resumen de tests (passed/failed)
- Links a reportes HTML
- Listado de fallos si existen
