package  helpers;

import  domain.model.Solicitud;

import java.util.List;

/**
 * Calculador de prioridad que orquesta todas las reglas configuradas.
 * 
 * Responsabilidad:
 * - Aplicar todas las reglas a una solicitud
 * - Sumar los scores parciales
 * - Devolver la prioridad final
 * 
 * Open/Closed Principle: Agregar reglas no requiere modificar esta clase.
 */
public class PriorityCalculator {
    
    private final List<PriorityRule> reglas;

    public PriorityCalculator(List<PriorityRule> reglas) {
        this.reglas = reglas;
    }

    /**
     * Calcula la prioridad total de una solicitud aplicando todas las reglas.
     * 
     * @param solicitud La solicitud a evaluar
     * @return Score total de prioridad (mayor = más prioritaria)
     */
    public double calcularPrioridad(Solicitud solicitud) {
        return reglas.stream()
                .mapToDouble(regla -> regla.calcularScore(solicitud))
                .sum();
    }
}

