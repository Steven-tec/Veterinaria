package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dto.CitaDTO;
import com.steven.manejodesesiones.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/citas/reporte")
public class ReporteCitasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CitaDTO> listaCitas = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cita_medica");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CitaDTO cita = new CitaDTO();
                cita.setId(rs.getLong("id"));
                cita.setIdMascota(rs.getLong("id_mascota"));

                Date fechaSql = rs.getDate("fecha");
                if (fechaSql != null) {
                    cita.setFecha(fechaSql.toLocalDate());
                } else {
                    cita.setFecha(null);
                }

                cita.setHora(rs.getString("hora"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setIndicaciones(rs.getString("indicaciones"));
                cita.setVeterinario(rs.getString("veterinario"));
                cita.setEstado(rs.getString("estado"));
                listaCitas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las citas médicas.");
        }

        request.setAttribute("citas", listaCitas);
        request.getRequestDispatcher("/reporteCitas.jsp").forward(request, response);
    }
}

