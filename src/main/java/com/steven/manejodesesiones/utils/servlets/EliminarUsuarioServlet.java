package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/eliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {
    // Método para manejar solicitudes POST para eliminar un usuario
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro "id" de la solicitud
        String idStr = request.getParameter("id");
        // Validar que el parámetro no sea nulo ni vacío
        if (idStr == null || idStr.isEmpty()) {
            // Si no viene el id, redirigimos al listado de usuarios o mostramos error
            response.sendRedirect("crudUsuarios");
            return;
        }

        try {
            // Convertir el id recibido de String a entero
            int id = Integer.parseInt(idStr);

            // Abrir conexión y preparar sentencia SQL para eliminar el usuario por id
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?")) {
                ps.setInt(1, id);
                // Ejecutar la eliminación y obtener número de filas afectadas
                int filasAfectadas = ps.executeUpdate();

                // Si no se eliminó ningún usuario con ese id, mostrar mensaje en consola
                if (filasAfectadas == 0) {
                    System.out.println("No se eliminó usuario con id: " + id);
                }
            }
        } catch (NumberFormatException e) {
            // Capturar error si el id recibido no es un número válido y mostrar mensaje
            System.out.println("El id recibido no es un número válido: " + idStr);
        } catch (Exception e) {
            // Capturar cualquier otra excepción y mostrar la traza del error
            e.printStackTrace();
        }
        // Redirigir al listado de usuarios después de intentar eliminar
        response.sendRedirect("crudUsuarios");
    }
}


