package com.steven.manejodesesiones.utils.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual sin crear una nueva si no existe
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalida (elimina) la sesión existente para cerrar sesión
            session.invalidate();
        }
        // Redirige al usuario a la página de login después de cerrar sesión
        response.sendRedirect("login.jsp");
    }
}
