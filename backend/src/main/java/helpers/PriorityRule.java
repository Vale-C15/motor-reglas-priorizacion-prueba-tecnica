package  helpers;

import  domain.model.Solicitud;

/**
 * Contrato para reglas de priorización.
 * 
 * Cada implementación aplica un criterio específico y devuelve un score parcial.
 * 
 * Strategy Pattern: Permite agregar/modificar reglas sin cambiar el calculador.
 */
public interface PriorityRule {
    /**
     * Calcula el score de prioridad para una solicitud según esta regla.
     * 
     * @param solicitud La solicitud a evaluar
     * @return Score parcial (puede ser negativo, cero o positivo)
     */
    double calcularScore(Solicitud solicitud);
    
    /**
     * Nombre descriptivo de la regla (para debugging/logs).
     */
    String getNombre();
}

