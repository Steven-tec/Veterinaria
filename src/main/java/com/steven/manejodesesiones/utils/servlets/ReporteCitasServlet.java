package com.steven.manejodesesiones.utils.servlets;

// Importa la clase CitaDTO que representa los datos de una cita médica
import com.steven.manejodesesiones.utils.dto.CitaDTO;
// Importa la clase DBConnection para manejar la conexión a la base de datos
import com.steven.manejodesesiones.utils.DBConnection;

// Importa clases necesarias para servlets y manejo HTTP
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Define un servlet que se activa en la ruta /citas/reporte
@WebServlet("/citas/reporte")
public class ReporteCitasServlet extends HttpServlet {

    // Método que responde a solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lista para almacenar los objetos CitaDTO recuperados de la base de datos
        List<CitaDTO> listaCitas = new ArrayList<>();

        // Intenta establecer conexión, preparar y ejecutar consulta SQL
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cita_medica");
             ResultSet rs = ps.executeQuery()) {

            // Recorre cada fila del resultado de la consulta
            while (rs.next()) {
                // Crea un nuevo objeto CitaDTO
                CitaDTO cita = new CitaDTO();

                // Asigna el id de la cita desde la columna "id"
                cita.setId(rs.getLong("id"));
                // Asigna el id de la mascota asociada a la cita
                cita.setIdMascota(rs.getLong("id_mascota"));

                // Obtiene la fecha SQL y la convierte a LocalDate si no es nula
                Date fechaSql = rs.getDate("fecha");
                if (fechaSql != null) {
                    cita.setFecha(fechaSql.toLocalDate());
                } else {
                    // Si la fecha es nula, se asigna null
                    cita.setFecha(null);
                }

                // Asigna la hora de la cita
                cita.setHora(rs.getString("hora"));
                // Asigna el motivo de la cita médica
                cita.setMotivo(rs.getString("motivo"));
                // Asigna las indicaciones dadas en la cita
                cita.setIndicaciones(rs.getString("indicaciones"));
                // Asigna el nombre del veterinario responsable
                cita.setVeterinario(rs.getString("veterinario"));
                // Asigna el estado de la cita (activo, cancelado, etc.)
                cita.setEstado(rs.getString("estado"));

                // Añade la cita creada a la lista de citas
                listaCitas.add(cita);
            }

        } catch (SQLException e) {
            // En caso de error en la base de datos, imprime la traza del error
            e.printStackTrace();
            // Establece un atributo de error para mostrar en la vista JSP
            request.setAttribute("error", "Error al cargar las citas médicas.");
        }

        // Asigna la lista de citas al atributo "citas" para la vista JSP
        request.setAttribute("citas", listaCitas);
        // Redirige la petición a la página JSP reporteCitas.jsp para mostrar los datos
        request.getRequestDispatcher("/reporteCitas.jsp").forward(request, response);
    }
}


