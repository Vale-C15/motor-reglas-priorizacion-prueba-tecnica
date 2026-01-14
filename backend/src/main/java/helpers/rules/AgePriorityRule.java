package  helpers.rules;

import  domain.model.Solicitud;
import  helpers.PriorityRule;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Regla que aumenta la prioridad según la antigüedad.
 * 
 * Lógica de negocio:
 * - Por cada hora de antigüedad, suma 1 punto (proporcional: 0.5 horas = 0.5 puntos)
 * - Máximo de 96 puntos (4 días)
 * 
 * Previene que solicitudes antiguas queden olvidadas.
 */
public class AgePriorityRule implements PriorityRule {
    
    private static final int MAX_POINTS = 96;
    
    @Override
    public double calcularScore(Solicitud solicitud) {
        Duration duracion = Duration.between(
            solicitud.getFechaCreacion(), 
            LocalDateTime.now()
        );
        
        // Convertir a horas con decimales (minutos incluidos)
        double horasAntiguedad = duracion.toMinutes() / 60.0;
        
        return Math.min(horasAntiguedad, MAX_POINTS);
    }

    @Override
    public String getNombre() {
        return "Antigüedad";
    }
}
