package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dao.MascotaDAO;
import com.steven.manejodesesiones.utils.dto.CitaDTO;
import com.steven.manejodesesiones.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/citas/listar")
public class ListarCitasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");

        List<CitaDTO> citas = new ArrayList<>();

        if (idUsuario != null) {
            // Cargar las mascotas del usuario
            request.setAttribute("mascotas", MascotaDAO.obtenerPorUsuario(idUsuario));

            try (Connection conn = DBConnection.getConnection()) {

                String sql = "SELECT * FROM cita_medica " +
                        "WHERE id_mascota IN (SELECT id FROM mascota WHERE id_usuario = ?) " +
                        "ORDER BY fecha DESC, hora DESC";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setLong(1, idUsuario);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    CitaDTO cita = new CitaDTO();
                    cita.setId(rs.getLong("id"));
                    cita.setIdMascota(rs.getLong("id_mascota"));
                    cita.setFecha(rs.getDate("fecha").toLocalDate());
                    cita.setHora(rs.getString("hora"));
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setIndicaciones(rs.getString("indicaciones"));
                    cita.setVeterinario(rs.getString("veterinario"));
                    cita.setEstado(rs.getString("estado"));
                    citas.add(cita);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al obtener las citas.");
            }

        } else {
            request.setAttribute("error", "Usuario no autenticado.");
        }

        request.setAttribute("citas", citas);
        request.getRequestDispatcher("/verCitas.jsp").forward(request, response);
    }
}
