'use client';

import { SolicitudForm } from '../components/SolicitudForm';
import { SolicitudList } from '../components/SolicitudList';
import { useSolicitudes } from '../hooks/useSolicitudes';
import { MainLayout } from '../templates/MainLayout';

/**
 * Página principal de la aplicación Next.js.
 * 
 * Responsabilidad: Orquestar componentes y estado global.
 */
export default function Home() {
  const { solicitudes, loading, error, crear } = useSolicitudes();

  return (
    <MainLayout>
      {error && (
        <div className="alert alert-error">
          {error}
        </div>
      )}
      
      <div className="app-grid">
        <aside className="sidebar">
          <SolicitudForm onSubmit={crear} />
        </aside>
        
        <section className="main-content">
          <SolicitudList 
            solicitudes={solicitudes} 
            loading={loading}
          />
        </section>
      </div>
    </MainLayout>
  );
}
