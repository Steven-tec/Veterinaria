package com.steven.manejodesesiones.utils.controlador;

import com.steven.manejodesesiones.utils.Usuario;
import com.steven.manejodesesiones.utils.servicio.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {

    private AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = authService.login(email, password);

        if (usuario != null && usuario.getRol() != null &&
                usuario.getRol().trim().equalsIgnoreCase("ADMINISTRADOR")) {

            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol());

            List<Usuario> usuarios = authService.obtenerTodosUsuarios();
            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("crudUsuarios.jsp").forward(request, response);
        } else {
            response.sendRedirect("errorAdmin.jsp");
        }
    }
}

