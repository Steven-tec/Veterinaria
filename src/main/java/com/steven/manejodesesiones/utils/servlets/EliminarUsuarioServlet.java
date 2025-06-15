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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            // Si no viene el id, redirigimos o mostramos error
            response.sendRedirect("crudUsuarios");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?")) {
                ps.setInt(1, id);
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas == 0) {
                    System.out.println("No se eliminó usuario con id: " + id);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("El id recibido no es un número válido: " + idStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("crudUsuarios");
    }
}

