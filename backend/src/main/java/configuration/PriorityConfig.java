package  configuration;

import  adapters.persistence.SolicitudInMemoryAdapter;
import  domain.usecase.CrearSolicitudUseCase;
import  domain.usecase.QueriesUseCase;
import  helpers.PriorityCalculator;
import  helpers.PriorityRule;
import  helpers.rules.AgePriorityRule;
import  helpers.rules.ManualPriorityRule;
import  helpers.rules.TypePriorityRule;
import  providers.SolicitudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de Spring para inyección de dependencias.
 * 
 * Responsabilidad: Wiring de las capas de la arquitectura.
 * 
 * IMPORTANTE: Aquí se configuran las reglas de priorización.
 * Para cambiar reglas, modificar el método priorityRules().
 */
@Configuration
public class PriorityConfig {

    // ===== Persistencia =====
    
    @Bean
    public SolicitudRepository solicitudRepository() {
        return new SolicitudInMemoryAdapter();
    }

    // ===== Motor de Reglas =====
    
    /**
     * Define las reglas activas del motor de priorización.
     * 
     * Para agregar/quitar reglas:
     * 1. Crear nueva clase que implemente PriorityRule
     * 2. Agregarla a esta lista
     * 3. NO requiere cambios en otras clases
     */
    @Bean
    public List<PriorityRule> priorityRules() {
        return List.of(
            new TypePriorityRule(),      // Peso por tipo de solicitud
            new AgePriorityRule(),        // Peso por antigüedad
            new ManualPriorityRule()      // Peso por prioridad manual
        );
    }

    @Bean
    public PriorityCalculator priorityCalculator(List<PriorityRule> rules) {
        return new PriorityCalculator(rules);
    }

    // ===== Casos de Uso =====
    
    @Bean
    public CrearSolicitudUseCase crearSolicitudUseCase(SolicitudRepository repository) {
        return new CrearSolicitudUseCase(repository);
    }

    @Bean
    public QueriesUseCase queriesUseCase(SolicitudRepository repository, 
                                            PriorityCalculator calculator) {
        return new QueriesUseCase(repository, calculator);
    }
}