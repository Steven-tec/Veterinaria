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

        // Obtener parámetros del formulario para la cita médica
        String idMascotaStr = request.getParameter("idMascota");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");
        String indicaciones = request.getParameter("indicaciones");
        String veterinario = request.getParameter("veterinario");

        // Validar que los campos obligatorios no estén vacíos
        if (idMascotaStr == null || idMascotaStr.isEmpty() ||
                fecha == null || fecha.isEmpty() ||
                hora == null || hora.isEmpty() ||
                veterinario == null || veterinario.isEmpty()) {

            request.setAttribute("error", "Por favor complete los campos obligatorios.");
            request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
            return;
        }

        try {
            // Convertir idMascota y idVeterinario a Long para la base de datos
            Long idMascota = Long.parseLong(idMascotaStr);
            Long idVeterinario = Long.parseLong(veterinario);

            try (Connection conn = DBConnection.getConnection()) {
                // Preparar la sentencia SQL para insertar la cita médica
                String sql = "INSERT INTO cita_medica (id_mascota, fecha, hora, motivo, indicaciones, veterinario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, idMascota);
                ps.setDate(2, Date.valueOf(fecha));
                ps.setString(3, hora);
                ps.setString(4, motivo);
                ps.setString(5, indicaciones);
                ps.setLong(6, idVeterinario);  // Guardar id del veterinario
                ps.setString(7, "ACTIVA");     // Estado inicial de la cita

                int filas = ps.executeUpdate();

                // Verificar si la inserción fue exitosa
                if (filas > 0) {
                    request.setAttribute("exito", "Cita médica agendada con éxito.");
                } else {
                    request.setAttribute("error", "No se pudo registrar la cita. Intente nuevamente.");
                }
            }
        } catch (NumberFormatException e) {
            // Captura error si el ID no es un número válido
            request.setAttribute("error", "ID inválido.");
        } catch (SQLException e) {
            // Captura error de base de datos y muestra traza para depuración
            e.printStackTrace();
            request.setAttribute("error", "Error en la base de datos.");
        }

        // Recarga la lista de veterinarios para mostrar en el formulario nuevamente
        VeterinarioDAO dao = new VeterinarioDAO();
        request.setAttribute("veterinarios", dao.listarVeterinarios());

        // Redirige nuevamente al JSP del formulario de registro de cita
        request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
    }
}

