package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;

import com.steven.manejodesesiones.utils.dao.VeterinarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registrarCita")
public class RegistrarCitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String idMascotaStr = request.getParameter("idMascota");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");
        String indicaciones = request.getParameter("indicaciones");
        String veterinario = request.getParameter("veterinario");

        if (idMascotaStr == null || idMascotaStr.isEmpty() ||
                fecha == null || fecha.isEmpty() ||
                hora == null || hora.isEmpty() ||
                veterinario == null || veterinario.isEmpty()) {

            request.setAttribute("error", "Por favor complete los campos obligatorios.");
            request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
            return;
        }

        try {
            Long idMascota = Long.parseLong(idMascotaStr);
            Long idVeterinario = Long.parseLong(veterinario);

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO cita_medica (id_mascota, fecha, hora, motivo, indicaciones, veterinario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, idMascota);
                ps.setDate(2, Date.valueOf(fecha));
                ps.setString(3, hora);
                ps.setString(4, motivo);
                ps.setString(5, indicaciones);
                ps.setLong(6, idVeterinario);  // Guardar id del veterinario
                ps.setString(7, "ACTIVA");

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    request.setAttribute("exito", "Cita médica agendada con éxito.");
                } else {
                    request.setAttribute("error", "No se pudo registrar la cita. Intente nuevamente.");
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID inválido.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en la base de datos.");
        }

        // Recarga la lista de veterinarios para el formulario
        VeterinarioDAO dao = new VeterinarioDAO();
        request.setAttribute("veterinarios", dao.listarVeterinarios());

        request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
    }
}

