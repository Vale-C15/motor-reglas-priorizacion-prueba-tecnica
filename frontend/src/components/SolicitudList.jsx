'use client';

import { LABELS_TIPOS } from '../const/tiposSolicitud';

/**
 * Componente: Lista de solicitudes priorizadas.
 * 
 * Props:
 * - solicitudes: Array de solicitudes con prioridad calculada
 * - loading: Indicador de carga
 */
export function SolicitudList({ solicitudes, loading }) {
  if (loading) {
    return <div className="loading">Cargando solicitudes...</div>;
  }

  if (solicitudes.length === 0) {
    return (
      <div className="empty-state">
        <p>No hay solicitudes registradas.</p>
        <p>Crea la primera solicitud usando el formulario.</p>
      </div>
    );
  }

  return (
    <div className="solicitud-list">
      <h2>Solicitudes Priorizadas</h2>
      <p className="subtitle">Ordenadas por prioridad calculada (mayor a menor)</p>
      
      <div className="solicitudes-container">
        {solicitudes.map((item, index) => (
          <SolicitudCard
            key={item.id}
            solicitud={item}
            posicion={index + 1}
          />
        ))}
      </div>
    </div>
  );
}

/**
 * Componente interno: Tarjeta individual de solicitud.
 */
function SolicitudCard({ solicitud, posicion }) {
  // Verificar que el objeto tiene la estructura esperada
  if (!solicitud || solicitud.prioridadCalculada === undefined) {
    console.error('Estructura de datos inválida:', solicitud);
    return null;
  }
  
  const s = solicitud;
  const prioridad = solicitud.prioridadCalculada;
  
  const getTipoClass = (tipo) => {
    switch (tipo) {
      case 'INCIDENTE': return 'tipo-incidente';
      case 'REQUERIMIENTO': return 'tipo-requerimiento';
      case 'CONSULTA': return 'tipo-consulta';
      default: return '';
    }
  };

  const formatFecha = (fecha) => {
    return new Date(fecha).toLocaleString('es-ES', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  return (
    <div className={`solicitud-card ${getTipoClass(s.tipo)}`}>
      <div className="card-header">
        <span className="posicion">#{posicion}</span>
        <span className={`tipo-badge ${getTipoClass(s.tipo)}`}>
          {LABELS_TIPOS[s.tipo]}
        </span>
        <span className="prioridad-calculada">
          Score: {prioridad.toFixed(1)}
        </span>
      </div>
      
      <div className="card-body">
        <div className="info-row">
          <span className="label">ID:</span>
          <span className="value">{s.id}</span>
        </div>
        
        <div className="info-row">
          <span className="label">Usuario:</span>
          <span className="value">{s.usuario}</span>
        </div>
        
        <div className="info-row">
          <span className="label">Prioridad Manual:</span>
          <span className="value">
            {'⭐'.repeat(s.prioridadManual)} ({s.prioridadManual}/5)
          </span>
        </div>
        
        <div className="info-row">
          <span className="label">Creada:</span>
          <span className="value">{formatFecha(s.fechaCreacion)}</span>
        </div>
      </div>
    </div>
  );
}
