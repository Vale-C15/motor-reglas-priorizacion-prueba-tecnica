package  domain.usecase;

import  domain.model.Solicitud;
import  domain.model.TipoSolicitud;
import  providers.SolicitudRepository;

/**
 * Caso de uso: Crear una nueva solicitud.
 * 
 * Responsabilidad:
 * - Validar datos de entrada
 * - Crear entidad de dominio
 * - Persistir
 */
public class CrearSolicitudUseCase {
    
    private final SolicitudRepository repository;

    public CrearSolicitudUseCase(SolicitudRepository repository) {
        this.repository = repository;
    }

    /**
     * Ejecuta el caso de uso de creación.
     * 
     * @return La solicitud creada con su ID generado
     */
    public Solicitud ejecutar(TipoSolicitud tipo, int prioridadManual, String usuario) {
        Solicitud solicitud = Solicitud.crear(tipo, prioridadManual, usuario);
        return repository.guardar(solicitud);
    }
}

