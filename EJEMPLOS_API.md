# Ejemplos de Pruebas - API

## Crear Solicitudes

### 1. Incidente con alta prioridad manual

```bash
curl -X POST http://localhost:8080/api/solicitudes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "INCIDENTE",
    "prioridadManual": 5,
    "usuario": "ana.garcia"
  }'
```

### 2. Requerimiento con prioridad media

```bash
curl -X POST http://localhost:8080/api/solicitudes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "REQUERIMIENTO",
    "prioridadManual": 3,
    "usuario": "carlos.ruiz"
  }'
```

### 3. Consulta con baja prioridad

```bash
curl -X POST http://localhost:8080/api/solicitudes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "CONSULTA",
    "prioridadManual": 2,
    "usuario": "laura.martinez"
  }'
```

## Listar Solicitudes

### Todas las solicitudes (sin orden)

```bash
curl http://localhost:8080/api/solicitudes
```

### Solicitudes priorizadas (ordenadas por score)

```bash
curl http://localhost:8080/api/solicitudes/priorizadas
```

## Ejemplo de Respuesta Priorizada

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "tipo": "INCIDENTE",
    "prioridadManual": 5,
    "fechaCreacion": "2026-01-13T10:00:00",
    "usuario": "ana.garcia",
    "prioridadCalculada": 174
  },
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "tipo": "REQUERIMIENTO",
    "prioridadManual": 3,
    "fechaCreacion": "2026-01-14T09:00:00",
    "usuario": "carlos.ruiz",
    "prioridadCalculada": 81
  },
  {
    "id": "550e8400-e29b-41d4-a716-446655440002",
    "tipo": "CONSULTA",
    "prioridadManual": 2,
    "fechaCreacion": "2026-01-14T10:30:00",
    "usuario": "laura.martinez",
    "prioridadCalculada": 30.5
  }
]
```

## Interpretación de Prioridades

### Ejemplo 1: Score 174.5
- Tipo INCIDENTE: +100 puntos
- Antigüedad (24 horas): +24 puntos
- Prioridad manual (5): +50 puntos
- **Total: 174 puntos**

### Ejemplo 2: Score 81.2
- Tipo REQUERIMIENTO: +50 puntos
- Antigüedad (1 hora): +1 punto
- Prioridad manual (3): +30 puntos
- **Total: 81 puntos**

### Ejemplo 3: Score 30.5
- Tipo CONSULTA: +10 puntos
- Antigüedad (0.5 horas): +0.5 puntos
- Prioridad manual (2): +20 puntos
- **Total: 30.5 puntos**

## Verificación del Motor de Reglas

1. Crea varias solicitudes con diferentes tipos y prioridades
2. Espera algunas horas
3. Consulta `/api/solicitudes/priorizadas`
4. Verifica que:
   - Los INCIDENTES tienen mayor prioridad
   - Las solicitudes más antiguas suben de posición
   - La prioridad manual influye correctamente

## Script de Prueba Completo (PowerShell)

```powershell
# Crear solicitudes de prueba
$baseUrl = "http://localhost:8080/api/solicitudes"

# Incidente urgente
Invoke-RestMethod -Uri $baseUrl -Method POST -ContentType "application/json" -Body '{
  "tipo": "INCIDENTE",
  "prioridadManual": 5,
  "usuario": "admin"
}'

# Requerimiento normal
Invoke-RestMethod -Uri $baseUrl -Method POST -ContentType "application/json" -Body '{
  "tipo": "REQUERIMIENTO",
  "prioridadManual": 3,
  "usuario": "developer"
}'

# Consulta simple
Invoke-RestMethod -Uri $baseUrl -Method POST -ContentType "application/json" -Body '{
  "tipo": "CONSULTA",
  "prioridadManual": 2,
  "usuario": "support"
}'

# Ver resultado priorizado
Invoke-RestMethod -Uri "$baseUrl/priorizadas" | ConvertTo-Json -Depth 5
```
