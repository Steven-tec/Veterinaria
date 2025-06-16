package com.steven.manejodesesiones.utils.servlets;

// Importa la clase para manejar conexiones a la base de datos
import com.steven.manejodesesiones.utils.DBConnection;
// Importa clases necesarias para trabajar con servlets y HTTP
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

// Define un servlet que responde a la ruta /verify
@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {

    // Método que maneja solicitudes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtiene los parámetros "email" y "codigo" enviados desde el formulario
        String email = request.getParameter("email");
        String codigo = request.getParameter("codigo");

        try (Connection conn = DBConnection.getConnection()) {
            // Prepara la consulta SQL para actualizar el campo 'verificado' a 1
            // solo si el email y el código de verificación coinciden en la tabla usuarios
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET verificado = 1 WHERE email = ? AND codigo_verificacion = ?");
            ps.setString(1, email);
            ps.setString(2, codigo);

            // Ejecuta la actualización y obtiene la cantidad de filas afectadas
            int updated = ps.executeUpdate();

            // Si se actualizó al menos un registro, la verificación fue exitosa
            if (updated > 0) {
                // Redirige a una página que indica éxito en la verificación
                response.sendRedirect("verificacionExitosa.jsp");
            } else {
                // Si no se actualizó ningún registro, el código o email son incorrectos
                // Redirige nuevamente a la página de verificación con un parámetro de error
                response.sendRedirect("verify.jsp?email=" + email + "&error=1");
            }
        } catch (Exception e) {
            // En caso de error (como problemas con la base de datos), imprime la traza del error
            e.printStackTrace();
            // Redirige a la página de verificación con un parámetro que indica error de servidor
            response.sendRedirect("verify.jsp?email=" + email + "&error=2");
        }
    }
}
