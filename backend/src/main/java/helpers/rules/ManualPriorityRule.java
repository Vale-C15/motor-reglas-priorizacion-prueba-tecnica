package  helpers.rules;

import  domain.model.Solicitud;
import  helpers.PriorityRule;

/**
 * Regla que aplica la prioridad manual indicada por el usuario.
 * 
 * Lógica de negocio:
 * - Multiplica la prioridad manual (1-5) por 10
 * - Rango: 10 a 50 puntos
 * 
 */
public class ManualPriorityRule implements PriorityRule {
    
    private static final int MULTIPLICADOR = 10;
    
    @Override
    public double calcularScore(Solicitud solicitud) {
        return solicitud.getPrioridadManual() * MULTIPLICADOR;
    }

    @Override
    public String getNombre() {
        return "Prioridad Manual";
    }
}
