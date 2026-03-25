#!/bin/bash
#
# Archivo de configuración para el proyecto Serenity BDD
# Personalización de skills y agentes
#

# Configuración de skills
SERENITY_SKILL_ENABLED=true
SERENITY_SKILL_PATH=".claude/skills/serenity-bdd-test-gen"

# Configuración de agentes
TEST_RUNNER_AGENT_ENABLED=true
TEST_RUNNER_AGENT_PATH=".claude/agents/test-runner.md"

# Rutas del proyecto
JAVA_SOURCE_PATH="src/test/java/com/projectaicopilot"
FEATURES_PATH="src/test/resources/features"
RUNNERS_PATH="src/test/java/com/projectaicopilot/runners"

# Configuración de Maven
MAVEN_GOALS="clean test"
MAVEN_PROFILES="test"

echo "✅ Configuración de project-ai-copilot iniciada"
         