package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.Usuario;
import com.steven.manejodesesiones.utils.servicio.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
    private AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = authService.login(email, password);

        // Verifica que el usuario no sea null, que esté activo y verificado
        if (usuario != null && usuario.isActivo() && usuario.isVerificado()) {
            // Invalida la sesión anterior si existe
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Crea una nueva sesión
            HttpSession session = request.getSession(true);

            // Establece el tiempo de expiración de la sesión en segundos (ej. 10 minutos)
            session.setMaxInactiveInterval(10 * 60);

            // Guarda atributos en sesión
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol());

            response.sendRedirect("RegistrarMascota.jsp");

        } else {
            // Redirige con error si no se cumple alguna condición
            response.sendRedirect("login2.jsp?error=credenciales");
        }
    }
}