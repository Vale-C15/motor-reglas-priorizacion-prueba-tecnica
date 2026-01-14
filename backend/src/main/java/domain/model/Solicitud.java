package  domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa una solicitud en el sistema.
 * 
 */
public class Solicitud {
    private final String id;
    private final TipoSolicitud tipo;
    private final int prioridadManual; // 1 (baja) a 5 (alta)
    private final LocalDateTime fechaCreacion;
    private final String usuario;

    // Constructor privado - usar Builder para validaciones
    private Solicitud(String id, TipoSolicitud tipo, int prioridadManual, 
                        LocalDateTime fechaCreacion, String usuario) {
        this.id = id;
        this.tipo = tipo;
        this.prioridadManual = prioridadManual;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }

    // Factory method para crear nuevas solicitudes
    public static Solicitud crear(TipoSolicitud tipo, int prioridadManual, String usuario) {
        validarPrioridadManual(prioridadManual);
        validarUsuario(usuario);
        
        return new Solicitud(
            UUID.randomUUID().toString(),
            tipo,
            prioridadManual,
            LocalDateTime.now(),
            usuario
        );
    }

    // Para rehidratar desde persistencia
    public static Solicitud desde(String id, TipoSolicitud tipo, int prioridadManual, 
                                    LocalDateTime fechaCreacion, String usuario) {
        return new Solicitud(id, tipo, prioridadManual, fechaCreacion, usuario);
    }

    private static void validarPrioridadManual(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad manual debe estar entre 1 y 5");
        }
    }

    private static void validarUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    // Getters (no setters - inmutabilidad)
    public String getId() {
        return id;
    }

    public TipoSolicitud getTipo() {
        return tipo;
    }

    public int getPrioridadManual() {
        return prioridadManual;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }
}
