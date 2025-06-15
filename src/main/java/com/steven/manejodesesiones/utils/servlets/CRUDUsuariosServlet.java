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
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, apellido, email, rol, activo, verificado, fecha_registro, fecha_actualizacion FROM usuarios ORDER BY id_usuario";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getLong("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));
                u.setActivo(rs.getBoolean("activo"));
                u.setVerificado(rs.getBoolean("verificado"));
                Timestamp regTs = rs.getTimestamp("fecha_registro");
                if (regTs != null) u.setFechaRegistro(regTs.toLocalDateTime());
                Timestamp updTs = rs.getTimestamp("fecha_actualizacion");
                if (updTs != null) u.setFechaActualizacion(updTs.toLocalDateTime());

                usuarios.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/crudUsuarios.jsp");
        dispatcher.forward(request, response);
    }
}

