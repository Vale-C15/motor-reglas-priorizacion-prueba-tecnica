package  domain.model;

/**
 * Tipos de solicitudes que pueden ser gestionadas por el sistema.
 * 
 * INCIDENTE tiene la mayor prioridad base (urgente, bloquea operaciones)
 * REQUERIMIENTO prioridad media (nuevas funcionalidades)
 * CONSULTA prioridad baja (información, dudas)
 */
public enum TipoSolicitud {
    INCIDENTE,
    REQUERIMIENTO,
    CONSULTA
}