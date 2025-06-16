// Paquete donde se encuentra la clase DAO para veterinarios
package com.steven.manejodesesiones.utils.dao;

// Importa la clase de conexión a la base de datos
import com.steven.manejodesesiones.utils.DBConnection;
// Importa el DTO que representa un veterinario
import com.steven.manejodesesiones.utils.dto.VeterinarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Clase DAO responsable de acceder a los datos de los veterinarios en la base de datos
public class VeterinarioDAO {

    // Método que devuelve una lista de todos los veterinarios desde la base de datos
    public List<VeterinarioDTO> listarVeterinarios() {
        // Lista donde se almacenarán los veterinarios obtenidos
        List<VeterinarioDTO> lista = new ArrayList<>();
        // Consulta SQL para seleccionar id y nombre de todos los veterinarios
        String sql = "SELECT id, nombre FROM veterinarios";

        // Bloque try-with-resources que asegura el cierre de la conexión, statement y resultset
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Itera sobre el resultado de la consulta
            while (rs.next()) {
                // Crea una nueva instancia del DTO
                VeterinarioDTO v = new VeterinarioDTO();
                // Asigna el ID del veterinario desde el resultado
                v.setId(rs.getLong("id"));
                // Asigna el nombre del veterinario desde el resultado
                v.setNombre(rs.getString("nombre"));
                // Agrega el veterinario a la lista
                lista.add(v);
            }
        } catch (Exception e) {
            // En caso de error, imprime la excepción en consola
            e.printStackTrace();
        }

        // Retorna la lista de veterinarios obtenidos
        return lista;
    }
}

