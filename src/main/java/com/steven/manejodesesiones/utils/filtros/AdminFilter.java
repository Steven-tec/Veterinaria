package com.steven.manejodesesiones.utils.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/crudUsuarios", "/reporteCitas"})
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false); // no crear sesión nueva si no existe

        if (session != null && "ADMINISTRADOR".equals(session.getAttribute("rol"))) {
            chain.doFilter(request, response); // permitir acceso
        } else {
            res.sendRedirect("errorAcceso.jsp"); // denegar acceso
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}
}
