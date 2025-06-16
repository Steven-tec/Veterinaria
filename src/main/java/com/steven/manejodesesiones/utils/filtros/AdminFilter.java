// Paquete que contiene los filtros para el manejo de seguridad y acceso
package com.steven.manejodesesiones.utils.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Filtro que se aplica a las URLs /crudUsuarios y /reporteCitas para controlar acceso de administrador
@WebFilter({"/crudUsuarios", "/reporteCitas"})
public class AdminFilter implements Filter {

    // Método principal del filtro que intercepta las peticiones antes de llegar al servlet o JSP
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Convertir los objetos genéricos ServletRequest y ServletResponse a HTTP específicos
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener la sesión existente, pero sin crear una nueva si no existe
        HttpSession session = req.getSession(false);

        // Verificar si la sesión existe y si el atributo "rol" es "ADMINISTRADOR"
        if (session != null && "ADMINISTRADOR".equals(session.getAttribute("rol"))) {
            // Si es administrador, permitir que la petición continúe su ejecución normalmente
            chain.doFilter(request, response);
        } else {
            // Si no tiene sesión o no es administrador, redirigir a página de error de acceso
            res.sendRedirect("errorAcceso.jsp");
        }
    }

    // Método para inicialización del filtro (vacío porque no se necesita configuración especial)
    public void init(FilterConfig filterConfig) throws ServletException {}

    // Método para liberar recursos cuando se destruye el filtro (vacío porque no hay recursos que liberar)
    public void destroy() {}
}
