# 🎯 Motor de Reglas de Priorización

Sistema de gestión de solicitudes con motor de priorización configurable basado en reglas de negocio.

## 📋 Descripción

Esta aplicación permite registrar solicitudes (incidentes, requerimientos, consultas) y calcular dinámicamente su prioridad de atención según reglas configurables. La priorización **no es estática**, sino que se calcula en tiempo real considerando múltiples factores.

### Reglas de Priorización Implementadas

1. **Tipo de Solicitud** (TypePriorityRule)
   - INCIDENTE: +100 puntos
   - REQUERIMIENTO: +50 puntos
   - CONSULTA: +10 puntos

2. **Antigüedad** (AgePriorityRule)
   - +1 punto por cada hora de antigüedad
   - Máximo: 72 puntos (3 días)

3. **Prioridad Manual** (ManualPriorityRule)
   - Multiplicador × 10
   - Rango: 10-50 puntos

**Prioridad Final = Suma de todas las reglas**

---

## 🏗️ Arquitectura

### Clean Architecture + DDD Light

```
┌─────────────────────────────────────────────┐
│          Entrypoints (Controllers)          │  ← HTTP/REST
├─────────────────────────────────────────────┤
│          Use Cases (Lógica Negocio)         │  ← Orquestación
├─────────────────────────────────────────────┤
│       Domain Model (Entidades Puras)        │  ← Núcleo
├─────────────────────────────────────────────┤
│      Helpers (Motor de Reglas - Strategy)   │  ← Algoritmos
├─────────────────────────────────────────────┤
│      Providers + Adapters (Persistencia)    │  ← Infraestructura
└─────────────────────────────────────────────┘
```

### Decisiones Arquitectónicas

#### ✅ Por qué Strategy Pattern para las reglas?

- **Extensibilidad**: Agregar nuevas reglas sin modificar código existente (Open/Closed Principle)
- **Testabilidad**: Cada regla es una unidad independiente fácil de probar
- **Mantenibilidad**: Lógica de priorización desacoplada y clara
- **Configurabilidad**: Activar/desactivar reglas desde `PriorityConfig`

#### ✅ Por qué Clean Architecture simplificada?

- **Independencia de frameworks**: El dominio no depende de Spring
- **Testeable**: Casos de uso pueden probarse sin infraestructura
- **Sustituible**: Cambiar de persistencia (InMemory → H2 → PostgreSQL) sin tocar lógica
- **Clara**: Separación de responsabilidades por capas

---

## 🚀 Ejecución

### Prerrequisitos

- **Java 17+** (OpenJDK o similar)
- **Gradle** (incluido Gradle Wrapper, no requiere instalación)
- **Node.js 18+** con npm

### Backend (Puerto 8080)

```bash
cd backend
.\gradlew.bat build -x test    # Windows
./gradlew build -x test        # Linux/Mac

.\gradlew.bat bootRun          # Windows
./gradlew bootRun              # Linux/Mac
```

La API estará disponible en: `http://localhost:8080/api`

### Frontend (Puerto 3000)

```bash
cd frontend
npm install
npm run dev
```

La UI estará disponible en: `http://localhost:3000`

---

## 📡 API Endpoints

### POST `/api/solicitudes`
Crea una nueva solicitud.

**Request:**
```json
{
  "tipo": "INCIDENTE",
  "prioridadManual": 4,
  "usuario": "juan.perez"
}
```

**Response:** (201 Created)
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "tipo": "INCIDENTE",
  "prioridadManual": 4,
  "fechaCreacion": "2026-01-14T10:30:00",
  "usuario": "juan.perez",
  "prioridadCalculada": null
}
```

### GET `/api/solicitudes`
Lista todas las solicitudes (sin ordenar).

### GET `/api/solicitudes/priorizadas`
**Endpoint principal**: Lista solicitudes ordenadas por prioridad calculada (mayor a menor).

**Response:**
```json
[
  {
    "id": "...",
    "tipo": "INCIDENTE",
    "prioridadManual": 5,
    "fechaCreacion": "2026-01-13T08:00:00",
    "usuario": "ana.garcia",
    "prioridadCalculada": 174.5
  },
  {
    "id": "...",
    "tipo": "REQUERIMIENTO",
    "prioridadManual": 3,
    "fechaCreacion": "2026-01-14T09:00:00",
    "usuario": "carlos.ruiz",
    "prioridadCalculada": 81.2
  }
]
```

---

## 🧪 Cómo Agregar una Nueva Regla

1. **Crear la regla** en `backend/helpers/rules/`:

```java
public class UrgencyPriorityRule implements PriorityRule {
    @Override
    public double calcularScore(Solicitud solicitud) {
        // Tu lógica aquí
        return score;
    }
    
    @Override
    public String getNombre() {
        return "Urgencia";
    }
}
```

2. **Registrar en configuración** (`PriorityConfig.java`):

```java
@Bean
public List<PriorityRule> priorityRules() {
    return List.of(
        new TypePriorityRule(),
        new AgePriorityRule(),
        new ManualPriorityRule(),
        new UrgencyPriorityRule()  // ← Nueva regla
    );
}
```

**¡Listo!** No se requiere modificar ninguna otra clase.

---

## 📦 Estructura del Proyecto

```
motor-priorizacion-prueba-tecnica/
├── backend/
│   ├── adapters/persistence/       # Implementaciones de persistencia
│   ├── configuration/              # Configuración Spring (beans)
│   ├── domain/
│   │   ├── model/                  # Entidades del dominio
│   │   └── usecase/                # Casos de uso (lógica negocio)
│   ├── entrypoints/rest/           # Controllers REST
│   ├── helpers/
│   │   ├── rules/                  # Reglas de priorización
│   │   ├── PriorityCalculator.java
│   │   └── PriorityRule.java
│   ├── providers/                  # Interfaces de persistencia
│   ├── PrioritizationApplication.java
│   ├── build.gradle                # Configuración Gradle
│   └── gradlew.bat                 # Gradle Wrapper (Windows)
│
├── frontend/
│   ├── src/
│   │   ├── app/                    # Next.js App Router
│   │   │   ├── layout.js           # Layout raíz
│   │   │   ├── page.js             # Página principal
│   │   │   └── globals.css         # Estilos globales
│   │   ├── components/             # Componentes reutilizables
│   │   ├── const/                  # Constantes
│   │   ├── hooks/                  # Custom hooks
│   │   ├── lib/                    # Cliente API
│   │   └── templates/              # Layouts
│   ├── package.json
│   └── next.config.js              # Configuración Next.js
│
└── README.md
```

---

## 💡 Mejoras Futuras (Fuera del Alcance)

- Persistencia real (H2, PostgreSQL)
- Autenticación y autorización
- Paginación en listados
- Filtros avanzados
- Logs estructurados
- Métricas y monitoreo
- Tests unitarios y de integración
- Swagger/OpenAPI
- Validaciones con Bean Validation
- Manejo de excepciones centralizado


---

## 📝 Notas Importantes

1. **Persistencia en memoria**: Los datos se pierden al reiniciar. Ideal para pruebas.
2. **CORS abierto**: Configurado para desarrollo. En producción, restringir orígenes.
3. **Sin validaciones exhaustivas**: Se priorizó claridad sobre validaciones complejas.
4. **Java 17+**: Se usan features modernas (Records, Switch Expressions).
5. **React sin state management**: Para este alcance, `useState` + custom hooks es suficiente.
