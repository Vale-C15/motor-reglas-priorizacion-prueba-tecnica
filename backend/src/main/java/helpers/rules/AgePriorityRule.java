package  helpers.rules;

import  domain.model.Solicitud;
import  helpers.PriorityRule;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Regla que aumenta la prioridad según la antigüedad.
 * 
 * Lógica de negocio:
 * - Por cada hora de antigüedad, suma 1 punto
 * - Máximo de 96 puntos (4 días)
 * 
 * Previene que solicitudes antiguas queden olvidadas.
 */
public class AgePriorityRule implements PriorityRule {
    
    private static final int MAX_POINTS = 96;
    
    @Override
    public double calcularScore(Solicitud solicitud) {
        long horasAntiguedad = Duration.between(
            solicitud.getFechaCreacion(), 
            LocalDateTime.now()
        ).toHours();
        
        return Math.min(horasAntiguedad, MAX_POINTS);
    }

    @Override
    public String getNombre() {
        return "Antigüedad";
    }
}
