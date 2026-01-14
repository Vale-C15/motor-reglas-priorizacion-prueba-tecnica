'use client';

import { useState } from 'react';
import { TIPOS_SOLICITUD, LABELS_TIPOS } from '../const/tiposSolicitud';

/**
 * Componente: Formulario para crear nuevas solicitudes.
 * 
 * Props:
 * - onSubmit: Callback cuando se envía el formulario
 */
export function SolicitudForm({ onSubmit }) {
  const [formData, setFormData] = useState({
    tipo: TIPOS_SOLICITUD.REQUERIMIENTO,
    prioridadManual: 3,
    usuario: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'prioridadManual' ? parseInt(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.usuario.trim()) {
      alert('El usuario es obligatorio');
      return;
    }

    const success = await onSubmit(formData);
    
    if (success) {
      // Resetear formulario
      setFormData({
        tipo: TIPOS_SOLICITUD.REQUERIMIENTO,
        prioridadManual: 3,
        usuario: ''
      });
    }
  };

  return (
    <form onSubmit={handleSubmit} className="solicitud-form">
      <h2>Nueva Solicitud</h2>
      
      <div className="form-group">
        <label htmlFor="tipo">Tipo de Solicitud</label>
        <select
          id="tipo"
          name="tipo"
          value={formData.tipo}
          onChange={handleChange}
        >
          {Object.keys(TIPOS_SOLICITUD).map(key => (
            <option key={key} value={TIPOS_SOLICITUD[key]}>
              {LABELS_TIPOS[TIPOS_SOLICITUD[key]]}
            </option>
          ))}
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="prioridadManual">
          Prioridad Manual (1-5): {formData.prioridadManual}
        </label>
        <input
          type="range"
          id="prioridadManual"
          name="prioridadManual"
          min="1"
          max="5"
          value={formData.prioridadManual}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label htmlFor="usuario">Usuario</label>
        <input
          type="text"
          id="usuario"
          name="usuario"
          value={formData.usuario}
          onChange={handleChange}
          placeholder="Ej: juan.perez"
          required
        />
      </div>

      <button type="submit" className="btn-primary">
        Crear Solicitud
      </button>
    </form>
  );
}
