/**
 * Tipos de solicitudes disponibles en el sistema.
 * Debe coincidir con el enum TipoSolicitud del backend.
 */
export const TIPOS_SOLICITUD = {
  INCIDENTE: 'INCIDENTE',
  REQUERIMIENTO: 'REQUERIMIENTO',
  CONSULTA: 'CONSULTA'
};

/**
 * Etiquetas amigables para mostrar en la UI.
 */
export const LABELS_TIPOS = {
  [TIPOS_SOLICITUD.INCIDENTE]: 'Incidente',
  [TIPOS_SOLICITUD.REQUERIMIENTO]: 'Requerimiento',
  [TIPOS_SOLICITUD.CONSULTA]: 'Consulta'
};
