package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/activarDesactivarUsuario")
public class ActivarDesactivarUsuarioServlet extends HttpServlet {
    // Método que procesa la solicitud POST para activar o desactivar un usuario
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el id del usuario desde el parámetro enviado en la solicitud
        int id = Integer.parseInt(request.getParameter("id"));
        // Obtener el estado (activo/inactivo) desde el parámetro, se espera "true" o "false"
        boolean activo = Boolean.parseBoolean(request.getParameter("estado")); // estado = "true" o "false"

        try (Connection conn = DBConnection.getConnection()) {
            // Preparar la sentencia SQL para actualizar el campo 'activo' del usuario con el id dado
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET activo=? WHERE id_usuario=?");
            ps.setBoolean(1, activo);  // Establecer el nuevo estado
            ps.setInt(2, id);          // Establecer el id del usuario
            ps.executeUpdate();        // Ejecutar la actualización en la base de datos
            ps.close();
        } catch (Exception e) {
            // Imprimir el stack trace en caso de error en la conexión o ejecución de la consulta
            e.printStackTrace();
        }

        // Redirigir al usuario a la página de gestión de usuarios después de la operación
        response.sendRedirect("crudUsuarios");
    }
}


