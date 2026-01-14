import './globals.css';

export const metadata = {
  title: 'Motor de Reglas de Priorización',
  description: 'Sistema de gestión y priorización de solicitudes',
};

export default function RootLayout({ children }) {
  return (
    <html lang="es">
      <body>{children}</body>
    </html>
  );
}
