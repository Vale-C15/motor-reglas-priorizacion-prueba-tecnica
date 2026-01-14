/**
 * Configuración y constantes de la aplicación.
 * 
 * API_URL: URL base del backend Spring Boot
 */
export const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

export const ENDPOINTS = {
  SOLICITUDES: `${API_URL}/api/solicitudes`,
  SOLICITUDES_PRIORIZADAS: `${API_URL}/api/solicitudes/priorizadas`,
};
