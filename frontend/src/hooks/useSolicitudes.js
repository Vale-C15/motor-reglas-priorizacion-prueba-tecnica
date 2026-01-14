'use client';

import { useState, useEffect, useCallback } from 'react';
import { crearSolicitud, obtenerSolicitudesPriorizadas } from '../lib/api';

/**
 * Hook personalizado para gestionar el estado de solicitudes.
 * 
 * Encapsula:
 * - Estado de las solicitudes
 * - Operaciones CRUD
 * - Manejo de loading y errores
 */
export function useSolicitudes() {
  const [solicitudes, setSolicitudes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  /**
   * Carga las solicitudes priorizadas desde el backend.
   */
  const cargarSolicitudes = useCallback(async () => {
    setLoading(true);
    setError(null);
    
    try {
      const data = await obtenerSolicitudesPriorizadas();
      console.log('Datos recibidos del backend:', data);
      console.log('Primer elemento:', data[0]);
      setSolicitudes(data);
    } catch (err) {
      setError('Error al cargar las solicitudes. Verifica que el backend esté corriendo.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  }, []);

  /**
   * Crea una nueva solicitud y recarga la lista.
   */
  const crear = useCallback(async (nuevaSolicitud) => {
    setError(null);
    
    try {
      await crearSolicitud(nuevaSolicitud);
      await cargarSolicitudes(); // Recargar lista
      return true;
    } catch (err) {
      setError('Error al crear la solicitud. Verifica los datos ingresados.');
      console.error(err);
      return false;
    }
  }, [cargarSolicitudes]);

  // Cargar solicitudes al montar el componente
  useEffect(() => {
    cargarSolicitudes();
  }, [cargarSolicitudes]);

  return {
    solicitudes,
    loading,
    error,
    cargarSolicitudes,
    crear
  };
}
