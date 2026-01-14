/**
 * Cliente HTTP para comunicación con el backend.
 * Centraliza las llamadas a la API.
 */

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080/api';

/**
 * Crea una nueva solicitud.
 */
export async function crearSolicitud(solicitud) {
  const response = await fetch(`${API_BASE_URL}/solicitudes`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(solicitud),
  });

  if (!response.ok) {
    throw new Error('Error al crear solicitud');
  }

  return response.json();
}

/**
 * Obtiene todas las solicitudes sin orden específico.
 */
export async function obtenerSolicitudes() {
  const response = await fetch(`${API_BASE_URL}/solicitudes`);
  
  if (!response.ok) {
    throw new Error('Error al obtener solicitudes');
  }

  return response.json();
}

/**
 * Obtiene solicitudes ordenadas por prioridad calculada.
 */
export async function obtenerSolicitudesPriorizadas() {
  const response = await fetch(`${API_BASE_URL}/solicitudes/priorizadas`);
  
  if (!response.ok) {
    throw new Error('Error al obtener solicitudes priorizadas');
  }

  return response.json();
}
