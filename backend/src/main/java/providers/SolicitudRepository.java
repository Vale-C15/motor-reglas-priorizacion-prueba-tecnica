package  providers;

import  domain.model.Solicitud;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida (interfaz) para persistencia de solicitudes.
 * 
 * Permite al dominio ser agnóstico de la tecnología de persistencia.
 * Implementaciones posibles: InMemory, H2, PostgreSQL, MongoDB, etc.
 */
public interface SolicitudRepository {
    
    /**
     * Guarda una nueva solicitud.
     */
    Solicitud guardar(Solicitud solicitud);
    
    /**
     * Busca una solicitud por ID.
     */
    Optional<Solicitud> buscarPorId(String id);
    
    /**
     * Retorna todas las solicitudes.
     */
    List<Solicitud> buscarTodas();
}

