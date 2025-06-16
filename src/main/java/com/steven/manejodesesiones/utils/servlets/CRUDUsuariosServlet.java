package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import com.steven.manejodesesiones.utils.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/crudUsuarios")
public class CRUDUsuariosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lista para almacenar los usuarios obtenidos de la base de datos
        List<Usuario> usuarios = new ArrayList<>();

        // Consulta SQL para obtener datos relevantes de todos los usuarios ordenados por id_usuario
        String sql = "SELECT id_usuario, nombre, apellido, email, rol, activo, verificado, fecha_registro, fecha_actualizacion FROM usuarios ORDER BY id_usuario";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Recorrer el resultado de la consulta
            while (rs.next()) {
                Usuario u = new Usuario();

                // Asignar valores obtenidos a las propiedades del objeto Usuario
                u.setIdUsuario(rs.getLong("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));
                u.setActivo(rs.getBoolean("activo"));
                u.setVerificado(rs.getBoolean("verificado"));

                // Obtener y convertir las fechas de registro y actualización
                Timestamp regTs = rs.getTimestamp("fecha_registro");
                if (regTs != null) u.setFechaRegistro(regTs.toLocalDateTime());

                Timestamp updTs = rs.getTimestamp("fecha_actualizacion");
                if (updTs != null) u.setFechaActualizacion(updTs.toLocalDateTime());

                // Agregar el usuario a la lista
                usuarios.add(u);
            }
        } catch (Exception e) {
            // En caso de excepción, imprimir la pila de error
            e.printStackTrace();
        }

        // Guardar la lista de usuarios en la solicitud como atributo
        request.setAttribute("usuarios", usuarios);

        // Redirigir la petición a la página JSP que mostrará el listado de usuarios
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crudUsuarios.jsp");
        dispatcher.forward(request, response);
    }
}


