// Paquete donde se encuentra la clase DAO para operaciones con mascotas
package com.steven.manejodesesiones.utils.dao;

// Importa el DTO que representa una mascota
import com.steven.manejodesesiones.utils.dto.MascotaDTO;
// Importa la clase de conexión a la base de datos
import com.steven.manejodesesiones.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase DAO que se encarga del acceso a datos relacionados con mascotas
public class MascotaDAO {

    // Método estático que obtiene la lista de mascotas asociadas a un usuario por su ID
    public static List<MascotaDTO> obtenerPorUsuario(Long idUsuario) {
        // Lista donde se almacenarán las mascotas recuperadas
        List<MascotaDTO> mascotas = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Consulta SQL que obtiene id y nombre de mascotas asociadas al usuario
            String sql = "SELECT id, nombre FROM mascota WHERE id_usuario = ?";
            // Prepara la sentencia SQL con parámetro
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, idUsuario); // Asigna el ID del usuario al primer parámetro de la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta

            // Recorre los resultados y construye objetos MascotaDTO
            while (rs.next()) {
                MascotaDTO m = new MascotaDTO(); // Crea una nueva instancia del DTO
                m.setId(rs.getLong("id")); // Establece el ID desde el resultado
                m.setNombre(rs.getString("nombre")); // Establece el nombre desde el resultado
                mascotas.add(m); // Agrega la mascota a la lista
            }

        } catch (Exception e) {
            // Imprime el error en la consola si ocurre una excepción
            e.printStackTrace();
        }

        // Devuelve la lista de mascotas asociadas al usuario
        return mascotas;
    }
}
