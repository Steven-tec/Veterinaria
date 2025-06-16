package com.steven.manejodesesiones.utils.dao;

import com.steven.manejodesesiones.utils.DBConnection;
import com.steven.manejodesesiones.utils.dto.VeterinarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    public List<VeterinarioDTO> listarVeterinarios() {
        List<VeterinarioDTO> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM veterinarios";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VeterinarioDTO v = new VeterinarioDTO();
                v.setId(rs.getLong("id"));
                v.setNombre(rs.getString("nombre"));
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
