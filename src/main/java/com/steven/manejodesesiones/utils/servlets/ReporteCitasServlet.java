package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dao.MascotaDAO;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Permite que tanto GET como POST funcionen
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        if (idUsuario != null) {
            request.setAttribute("mascotas", MascotaDAO.obtenerPorUsuario(idUsuario));
        }

        List<CitaDTO> citas = new ArrayList<>();

        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String idMascota = request.getParameter("idMascota");

        try (Connection conn = DBConnection.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * FROM cita_medica WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (fechaInicio != null && !fechaInicio.isEmpty()) {
                sql.append(" AND fecha >= ?");
                params.add(LocalDate.parse(fechaInicio));
            }
            if (fechaFin != null && !fechaFin.isEmpty()) {
                sql.append(" AND fecha <= ?");
                params.add(LocalDate.parse(fechaFin));
            }
            if (idMascota != null && !idMascota.isEmpty()) {
                sql.append(" AND id_mascota = ?");
                params.add(Long.parseLong(idMascota));
            }

            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("citas", citas);
        request.getRequestDispatcher("/verCitas.jsp").forward(request, response);
    }
}
