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

    // Método para autenticar un usuario dado un email y una contraseña
    public Usuario login(String email, String password) {
        Usuario usuario = null;
        // Consulta SQL que busca usuarios activos con el email proporcionado
        String sql = "SELECT * FROM usuarios WHERE email = ? AND activo = 1"; // Solo usuarios activos

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Establecer el parámetro email en la consulta
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtener el hash almacenado de la contraseña
                    String hashedPassword = rs.getString("password_hash");

                    // Verificar la contraseña ingresada comparándola con el hash
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        // Si la contraseña es correcta, crear un objeto Usuario y llenar sus datos
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
            // Captura y muestra la excepción en caso de error en la conexión o consulta
            e.printStackTrace();
        }

        // Retorna el usuario autenticado o null si falla la autenticación
        return usuario;
    }

    // Método para obtener la lista completa de usuarios desde la base de datos
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Itera sobre cada fila del resultado para construir los objetos Usuario
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

                // Añadir el usuario a la lista
                usuarios.add(u);
            }
        } catch (Exception e) {
            // Captura y muestra la excepción en caso de error durante la consulta
            e.printStackTrace();
        }

        // Retorna la lista con todos los usuarios encontrados
        return usuarios;
    }
}

