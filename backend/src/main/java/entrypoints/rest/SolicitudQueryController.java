package  entrypoints.rest;

import  domain.model.Solicitud;
import  domain.model.TipoSolicitud;
import  domain.usecase.QueriesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para consultas (lecturas) de solicitudes.
 * 
 * Patrón: Query Handler
 * Responsabilidad: Procesar operaciones de solo lectura.
 * 
 * Separado de comandos siguiendo principios CQRS.
 */
@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudQueryController {
    
    private final QueriesUseCase queriesUseCase;

    public SolicitudQueryController(QueriesUseCase queriesUseCase) {
        this.queriesUseCase = queriesUseCase;
    }

    /**
     * GET /api/solicitudes
     * Lista todas las solicitudes (sin ordenar).
     */
    @GetMapping
    public ResponseEntity<List<SolicitudResponse>> listarTodas() {
        List<SolicitudResponse> responses = queriesUseCase.listarTodas().stream()
                .map(s -> toResponse(s, null))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/solicitudes/priorizadas
     * Lista solicitudes ordenadas por prioridad calculada.
     * 
     * Este es el endpoint principal del motor de reglas.
     */
    @GetMapping("/priorizadas")
    public ResponseEntity<List<SolicitudResponse>> listarPriorizadas() {
        List<SolicitudResponse> responses = queriesUseCase.listarPriorizadas().stream()
                .map(sp -> toResponse(sp.solicitud(), sp.prioridadCalculada()))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    // ===== DTOs =====

    /**
     * Response que incluye la prioridad calculada (si aplica).
     */
    public record SolicitudResponse(
        String id,
        TipoSolicitud tipo,
        int prioridadManual,
        LocalDateTime fechaCreacion,
        String usuario,
        Double prioridadCalculada // null si no se calculó
    ) {}

    private SolicitudResponse toResponse(Solicitud s, Double prioridadCalculada) {
        return new SolicitudResponse(
            s.getId(),
            s.getTipo(),
            s.getPrioridadManual(),
            s.getFechaCreacion(),
            s.getUsuario(),
            prioridadCalculada
        );
    }
}
