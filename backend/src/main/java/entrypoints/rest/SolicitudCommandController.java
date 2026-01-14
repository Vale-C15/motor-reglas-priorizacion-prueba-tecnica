package  entrypoints.rest;

import  domain.model.Solicitud;
import  domain.model.TipoSolicitud;
import  domain.usecase.CrearSolicitudUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador REST para comandos (escrituras) de solicitudes.
 * 
 * Patrón: Command Handler
 * Responsabilidad: Procesar operaciones que modifican el estado del sistema.
 * 
 * Separado de queries siguiendo principios CQRS.
 */
@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudCommandController {
    
    private final CrearSolicitudUseCase crearUseCase;

    public SolicitudCommandController(CrearSolicitudUseCase crearUseCase) {
        this.crearUseCase = crearUseCase;
    }

    /**
     * POST /api/solicitudes
     * Crea una nueva solicitud.
     * 
     * @return 201 Created con la solicitud creada
     * @return 400 Bad Request si los datos son inválidos
     */
    @PostMapping
    public ResponseEntity<SolicitudResponse> crear(@RequestBody CrearSolicitudRequest request) {
        try {
            Solicitud solicitud = crearUseCase.ejecutar(
                request.tipo(),
                request.prioridadManual(),
                request.usuario()
            );
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(toResponse(solicitud));
                    
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ===== DTOs =====

    /**
     * Request para crear solicitud.
     */
    public record CrearSolicitudRequest(
        TipoSolicitud tipo,
        int prioridadManual,
        String usuario
    ) {}

    /**
     * Response con datos de la solicitud creada.
     */
    public record SolicitudResponse(
        String id,
        TipoSolicitud tipo,
        int prioridadManual,
        LocalDateTime fechaCreacion,
        String usuario
    ) {}

    private SolicitudResponse toResponse(Solicitud s) {
        return new SolicitudResponse(
            s.getId(),
            s.getTipo(),
            s.getPrioridadManual(),
            s.getFechaCreacion(),
            s.getUsuario()
        );
    }
}
