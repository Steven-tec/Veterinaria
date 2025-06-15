package com.steven.manejodesesiones.utils.dao;

import com.steven.manejodesesiones.utils.dto.MascotaDTO;
import com.steven.manejodesesiones.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    public static List<MascotaDTO> obtenerPorUsuario(Long idUsuario) {
        List<MascotaDTO> mascotas = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id, nombre FROM mascota WHERE id_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MascotaDTO m = new MascotaDTO();
                m.setId(rs.getLong("id"));
                m.setNombre(rs.getString("nombre"));
                mascotas.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mascotas;
    }
}
