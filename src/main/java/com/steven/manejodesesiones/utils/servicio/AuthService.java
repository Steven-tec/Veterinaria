package com.steven.manejodesesiones.utils.servicio;

import com.steven.manejodesesiones.utils.DBConnection;
import com.steven.manejodesesiones.utils.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    public Usuario login(String email, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND activo = 1"; // Solo usuarios activos

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password_hash");

                    if (BCrypt.checkpw(password, hashedPassword)) {
                        usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("id_usuario"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setApellido(rs.getString("apellido"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setRol(rs.getString("rol"));
                        usuario.setActivo(rs.getBoolean("activo"));
                        usuario.setVerificado(rs.getBoolean("verificado"));
                        usuario.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                        usuario.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

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
                u.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                u.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());

                usuarios.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}

