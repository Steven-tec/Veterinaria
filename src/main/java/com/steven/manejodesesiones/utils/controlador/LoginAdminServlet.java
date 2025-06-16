// Paquete donde se encuentra este servlet controlador
package com.steven.manejodesesiones.utils.controlador;

// Importación de la clase Usuario (modelo del sistema)
import com.steven.manejodesesiones.utils.Usuario;
// Importación del servicio de autenticación
import com.steven.manejodesesiones.utils.servicio.AuthService;

// Importaciones necesarias para el funcionamiento del servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

// Anotación que registra este servlet con la ruta /LoginAdminServlet
@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {

    // Instancia del servicio de autenticación
    private AuthService authService = new AuthService();

    // Método que maneja las solicitudes HTTP POST (por ejemplo, desde un formulario)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene los parámetros enviados desde el formulario: email y contraseña
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Llama al método login del servicio para verificar credenciales
        Usuario usuario = authService.login(email, password);

        // Verifica si el usuario existe, tiene rol asignado y es ADMINISTRADOR
        if (usuario != null && usuario.getRol() != null &&
                usuario.getRol().trim().equalsIgnoreCase("ADMINISTRADOR")) {

            // Crea una sesión HTTP y guarda el usuario y su rol como atributos de sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol());

            // Obtiene la lista de todos los usuarios para mostrarla en la interfaz
            List<Usuario> usuarios = authService.obtenerTodosUsuarios();
            request.setAttribute("usuarios", usuarios);

            // Redirige internamente (forward) al JSP para gestionar los usuarios (vista CRUD)
            request.getRequestDispatcher("crudUsuarios.jsp").forward(request, response);
        } else {
            // Si el usuario no es válido o no es administrador, redirige a una página de error
            response.sendRedirect("errorAdmin.jsp");
        }
    }
}

