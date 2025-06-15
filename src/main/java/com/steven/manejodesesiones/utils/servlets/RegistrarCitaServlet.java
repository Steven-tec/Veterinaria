package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registrarCita")
public class RegistrarCitaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long idMascota = Long.parseLong(request.getParameter("idMascota"));
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");
        String indicaciones = request.getParameter("indicaciones");
        String veterinario = request.getParameter("veterinario");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO cita_medica (id_mascota, fecha, hora, motivo, indicaciones, veterinario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setLong(1, idMascota);
            ps.setDate(2, Date.valueOf(fecha));
            ps.setString(3, hora);
            ps.setString(4, motivo);
            ps.setString(5, indicaciones);
            ps.setString(6, veterinario);
            ps.setString(7, "ACTIVA");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 🔁 Redirige al servlet que carga y muestra las citas
        response.sendRedirect(request.getContextPath() + "/citas/reporte");
    }
}
