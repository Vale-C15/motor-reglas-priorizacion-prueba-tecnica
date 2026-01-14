/**
 * Template principal: Layout de la aplicación.
 */
export function MainLayout({ children }) {
  return (
    <div className="main-layout">
      <header className="header">
        <h1>🎯 Motor de Reglas de Priorización 🎯</h1>
        <p className="header-subtitle">Sistema de gestión de solicitudes con priorización inteligente</p>
      </header>
      
      <main className="content">
        {children}
      </main>
      
      <footer className="footer">
        <p>Prueba Técnica - Clean Architecture + Strategy Pattern</p>
      </footer>
    </div>
  );
}
