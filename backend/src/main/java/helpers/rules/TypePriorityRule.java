package  helpers.rules;

import  domain.model.Solicitud;
import  helpers.PriorityRule;

/**
 * Regla que asigna prioridad según el tipo de solicitud.
 * 
 * Lógica de negocio:
 * - INCIDENTE: +100 puntos (máxima prioridad)
 * - REQUERIMIENTO: +50 puntos
 * - CONSULTA: +10 puntos
 */
public class TypePriorityRule implements PriorityRule {
    
    @Override
    public double calcularScore(Solicitud solicitud) {
        return switch (solicitud.getTipo()) {
            case INCIDENTE -> 100.0;
            case REQUERIMIENTO -> 50.0;
            case CONSULTA -> 10.0;
        };
    }

    @Override
    public String getNombre() {
        return "Tipo de Solicitud";
    }
}
