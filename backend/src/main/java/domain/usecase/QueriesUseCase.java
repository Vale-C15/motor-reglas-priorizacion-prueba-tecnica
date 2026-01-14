package  domain.usecase;

import  domain.model.Solicitud;
import  helpers.PriorityCalculator;
import  providers.SolicitudRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Caso de uso: Consultas sobre solicitudes.
 * 
 * Agrupa todas las queries relacionadas con solicitudes:
 * - Listar todas
 * - Listar priorizadas
 */
public class QueriesUseCase {
    
    private final SolicitudRepository repository;
    private final PriorityCalculator calculator;

    public QueriesUseCase(SolicitudRepository repository, PriorityCalculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }

    /**
     * Retorna todas las solicitudes sin orden específico.
     */
    public List<Solicitud> listarTodas() {
        return repository.buscarTodas();
    }

    /**
     * Retorna las solicitudes ordenadas por prioridad calculada (mayor a menor).
     * 
     * Este es el endpoint principal del motor de reglas.
     */
    public List<SolicitudPriorizada> listarPriorizadas() {
        return repository.buscarTodas().stream()
                .map(solicitud -> new SolicitudPriorizada(
                    solicitud,
                    calculator.calcularPrioridad(solicitud)
                ))
                .sorted(Comparator.comparingDouble(SolicitudPriorizada::prioridadCalculada).reversed())
                .collect(Collectors.toList());
    }

    /**
     * DTO que envuelve una solicitud con su prioridad calculada.
     * 
     * Record de Java 14+ - inmutable por defecto.
     */
    public record SolicitudPriorizada(
        Solicitud solicitud,
        double prioridadCalculada
    ) {}
}
