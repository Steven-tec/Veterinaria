package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.Usuario;
import com.steven.manejodesesiones.utils.servicio.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Instancia del servicio de autenticación
    private AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros email y password desde la petición
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Intentar autenticar al usuario con email y password
        Usuario usuario = authService.login(email, password);

        // Verifica que el usuario no sea null, que esté activo y verificado
        if (usuario != null && usuario.isActivo() && usuario.isVerificado()) {
            // Invalida la sesión anterior si existe para evitar problemas de sesión fija
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Crea una nueva sesión para el usuario autenticado
            HttpSession session = request.getSession(true);

            // Establece el tiempo de expiración de la sesión en segundos (por ejemplo, 10 minutos)
            session.setMaxInactiveInterval(10 * 60);

            // Guarda atributos del usuario en la sesión para uso posterior
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol());

            // Redirige al usuario a la página para registrar una cita médica
            response.sendRedirect("registrarCita.jsp");

        } else {
            // Si la autenticación falla o el usuario no está activo/verificado,
            // redirige al login con un parámetro que indica error en las credenciales
            response.sendRedirect("login.jsp?error=credenciales");
        }
    }
}


