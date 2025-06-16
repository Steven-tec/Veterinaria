package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.Usuario;
import com.steven.manejodesesiones.utils.servicio.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
    // Instancia del servicio de autenticación
    private AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario de login
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Intentar autenticar usuario con el email y password recibidos
        Usuario usuario = authService.login(email, password);

        // Verifica que el usuario no sea null, que esté activo y verificado
        if (usuario != null && usuario.isActivo() && usuario.isVerificado()) {
            // Invalida la sesión anterior si existe para evitar sesión fija
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Crea una nueva sesión para el usuario autenticado
            HttpSession session = request.getSession(true);

            // Establece el tiempo de expiración de la sesión en segundos (ejemplo: 10 minutos)
            session.setMaxInactiveInterval(10 * 60);

            // Guarda atributos del usuario en la sesión para mantener el estado
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol());

            // Redirige a la página para registrar mascotas tras login exitoso
            response.sendRedirect("RegistrarMascota.jsp");

        } else {
            // Si falla la autenticación o no está activo/verificado, redirige con mensaje de error
            response.sendRedirect("login2.jsp?error=credenciales");
        }
    }
}
