// Paquete donde se definen filtros para la gestión de autenticación y autorización
package com.steven.manejodesesiones.utils.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Filtro que protege las páginas agenda.jsp y registrarCita.jsp para usuarios autenticados
@WebFilter(urlPatterns = {"/agenda.jsp", "/registrarCita.jsp"})
public class AuthFilter implements Filter {

    // Método que intercepta las solicitudes para verificar si el usuario está autenticado
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Convertir los objetos genéricos a tipos HTTP para acceder a métodos específicos
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener la sesión actual sin crear una nueva en caso de que no exista
        HttpSession session = req.getSession(false);

        // Variable que indica si el usuario está logueado, basado en la existencia del atributo "usuario"
        boolean logueado = session != null && session.getAttribute("usuario") != null;

        // Si no está autenticado, redirigir al usuario a la página de login
        if (!logueado) {
            res.sendRedirect("login.jsp");
            return; // Detener el procesamiento para evitar que continúe hacia las páginas protegidas
        }

        // Si está autenticado, continuar con la cadena de filtros y finalmente procesar la solicitud
        chain.doFilter(request, response);
    }

    // Método de inicialización del filtro, no se requiere configuración adicional aquí
    public void init(FilterConfig filterConfig) throws ServletException {}

    // Método llamado al destruir el filtro, no hay recursos que liberar en este caso
    public void destroy() {}
}
