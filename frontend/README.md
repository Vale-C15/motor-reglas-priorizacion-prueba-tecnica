# Motor de Reglas de Priorización - Frontend

Frontend desarrollado con **Next.js 14** (App Router) y **React 18**.

## 🚀 Inicio Rápido

### Prerrequisitos

- Node.js 18+ 
- Backend corriendo en `http://localhost:8080`

### Instalación

```bash
# Instalar dependencias
npm install

# Iniciar en modo desarrollo
npm run dev

# Abrir http://localhost:3000
```

### Scripts Disponibles

```bash
npm run dev      # Desarrollo con hot reload
npm run build    # Compilar para producción
npm run start    # Iniciar servidor producción
npm run lint     # Análisis de código
```

## 📁 Estructura del Proyecto

```
frontend/
├── src/
│   ├── app/                  # Next.js App Router
│   │   ├── layout.js         # Layout raíz
│   │   ├── page.js           # Página principal
│   │   └── globals.css       # Estilos globales
│   │
│   ├── components/           # Componentes reutilizables
│   │   ├── SolicitudForm.jsx
│   │   └── SolicitudList.jsx
│   │
│   ├── hooks/                # Custom hooks
│   │   └── useSolicitudes.js
│   │
│   ├── lib/                  # Utilidades y clientes
│   │   └── api.js
│   │
│   ├── const/                # Constantes
│   │   └── tiposSolicitud.js
│   │
│   ├── config/               # Configuración
│   │   └── api.js
│   │
│   └── templates/            # Layouts y plantillas
│       └── MainLayout.jsx
│
├── next.config.js            # Configuración Next.js
├── .env.local                # Variables de entorno
└── package.json
```

## 🔧 Configuración

### Variables de Entorno

Crear `.env.local` en la raíz del proyecto:

```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

## 🎨 Características

✅ **Next.js 14** con App Router  
✅ **React 18** con Client Components  
✅ **Custom Hooks** para lógica de negocio  
✅ **CSS puro** (sin frameworks, simple y limpio)  
✅ **Componentización clara**  
✅ **Gestión de errores**  

## 📝 Componentes Principales

### `SolicitudForm`
Formulario para crear nuevas solicitudes. Valida datos antes de enviar.

### `SolicitudList`
Lista las solicitudes ordenadas por prioridad calculada. Muestra tarjetas con información detallada.

### `useSolicitudes`
Hook personalizado que encapsula:
- Carga de solicitudes priorizadas
- Creación de nuevas solicitudes
- Estados de loading/error

## 🎯 Decisiones de Arquitectura

### ¿Por qué Next.js?

- ✅ SSR/SSG ready (escalable a futuro)
- ✅ App Router moderno
- ✅ File-based routing
- ✅ Optimizaciones automáticas

### ¿Por qué Client Components?

La aplicación es interactiva (formularios, estado) y no requiere SEO crítico.

### ¿Por qué CSS puro?

Para una prueba técnica: simple, sin dependencias extra, fácil de entender.

---

**Desarrollado para prueba técnica - Motor de Reglas de Priorización**
