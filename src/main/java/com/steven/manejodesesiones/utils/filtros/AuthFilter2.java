// Paquete donde se definen filtros para controlar acceso y autenticación
package com.steven.manejodesesiones.utils.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Filtro que protege la página RegistrarMascota.jsp para usuarios autenticados
@WebFilter(urlPatterns = {"/RegistrarMascota.jsp"})
public class AuthFilter2 implements Filter {

    // Método que intercepta las solicitudes para verificar la autenticación del usuario
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Conversión de los objetos genéricos a objetos HTTP para acceder a funcionalidades específicas
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener la sesión existente sin crear una nueva sesión si no existe
        HttpSession session = req.getSession(false);

        // Comprobar si el usuario está logueado verificando que la sesión y el atributo "usuario" existan
        boolean logueado = session != null && session.getAttribute("usuario") != null;

        // Si no está autenticado, redirigir al usuario a la página de login2.jsp
        if (!logueado) {
            res.sendRedirect("login2.jsp");
            return; // Termina la ejecución para evitar continuar hacia la página protegida
        }

        // Si el usuario está autenticado, continuar con el flujo normal de la petición
        chain.doFilter(request, response);
    }

    // Método de inicialización del filtro (vacío porque no se requiere configuración especial)
    public void init(FilterConfig filterConfig) throws ServletException {}

    // Método llamado al destruir el filtro (vacío porque no hay recursos que liberar)
    public void destroy() {}
}
