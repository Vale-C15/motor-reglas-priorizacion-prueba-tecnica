# Backend - Motor de Priorización

## Estructura

```
backend/
├── adapters/persistence/       → SolicitudInMemoryAdapter
├── configuration/              → PriorityConfig (Spring beans)
├── domain/
│   ├── model/                  → Solicitud, TipoSolicitud
│   └── usecase/                → CrearSolicitudUseCase, QueriesUseCase
├── entrypoints/rest/           → SolicitudQueryController
├── helpers/
│   ├── rules/                  → TypePriorityRule, AgePriorityRule, ManualPriorityRule
│   ├── PriorityCalculator.java
│   └── PriorityRule.java
├── providers/                  → SolicitudRepository (interfaz)
├── PrioritizationApplication.java
└── build.gradle
```

## Tecnologías

- Java 17+
- Spring Boot 3.2.0
- Gradle 8+ (incluye Gradle Wrapper)

## Ejecutar

```bash
# Compilar
.\gradlew.bat build -x test    # Windows
./gradlew build -x test        # Linux/Mac

# Ejecutar
.\gradlew.bat bootRun          # Windows
./gradlew bootRun              # Linux/Mac
```

La aplicación arrancará en: **http://localhost:8080**

## Endpoints

- `POST /api/solicitudes` - Crear solicitud
- `GET /api/solicitudes` - Listar todas
- `GET /api/solicitudes/priorizadas` - Listar priorizadas ⭐

## Motor de Reglas

Las reglas se configuran en `PriorityConfig.java`:

```java
@Bean
public List<PriorityRule> priorityRules() {
    return List.of(
        new TypePriorityRule(),
        new AgePriorityRule(),
        new ManualPriorityRule()
    );
}
```

Para agregar una regla:
1. Crear clase que implemente `PriorityRule`
2. Agregarla al método `priorityRules()`

## Arquitectura

Clean Architecture + Strategy Pattern:
- **Domain**: Entidades puras sin dependencias
- **Use Cases**: Lógica de aplicación
- **Adapters**: Implementaciones de infraestructura
- **Helpers**: Motor de reglas (extensible)
