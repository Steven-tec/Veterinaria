package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/editarUsuario")
public class EditarUsuarioServlet extends HttpServlet {
    // Método para manejar solicitudes GET (para mostrar datos actuales del usuario)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro 'id' enviado en la solicitud y convertirlo a entero
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DBConnection.getConnection()) {
            // Preparar consulta SQL para obtener datos del usuario con id específico
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id_usuario = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Si se encontró el usuario, obtener sus datos y guardarlos como atributos de la request
            if (rs.next()) {
                request.setAttribute("id", rs.getInt("id_usuario"));  // cambiar a id_usuario
                request.setAttribute("nombre", rs.getString("nombre"));
                request.setAttribute("apellido", rs.getString("apellido"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("rol", rs.getString("rol"));
            }

            // Cerrar ResultSet y PreparedStatement para liberar recursos
            rs.close();
            ps.close();
        } catch (Exception e) {
            // Manejo de excepción: imprimir la traza del error
            e.printStackTrace();
        }

        // Redirigir a la página JSP para editar usuario, enviando los atributos cargados
        RequestDispatcher dispatcher = request.getRequestDispatcher("editarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    // Método para manejar solicitudes POST (para actualizar datos del usuario)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos enviados desde el formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String rol = request.getParameter("rol");

        try (Connection conn = DBConnection.getConnection()) {
            // Preparar la consulta SQL para actualizar los datos del usuario con id dado
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE usuarios SET nombre=?, apellido=?, email=?, rol=? WHERE id_usuario = ?");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, rol);
            ps.setInt(5, id);
            ps.executeUpdate();

            // Cerrar el PreparedStatement para liberar recursos
            ps.close();
        } catch (Exception e) {
            // Manejo de excepción: imprimir la traza del error
            e.printStackTrace();
        }

        // Redirigir nuevamente al listado de usuarios luego de actualizar
        response.sendRedirect("crudUsuarios");
    }
}
