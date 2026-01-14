package adapters.persistence;

import domain.model.Solicitud;
import providers.SolicitudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación en memoria del repositorio de solicitudes.
 */
public class SolicitudInMemoryAdapter implements SolicitudRepository {
    
    // Thread-safe para entorno concurrente
    private final Map<String, Solicitud> store = new ConcurrentHashMap<>();

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        store.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public Optional<Solicitud> buscarPorId(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return new ArrayList<>(store.values());
    }
}
