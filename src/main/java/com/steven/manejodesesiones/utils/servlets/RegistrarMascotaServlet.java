package com.steven.manejodesesiones.utils.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/registrarMascota")
public class RegistrarMascotaServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/veterinaria_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombre");  // nombre usuario
        String nombreMascota = request.getParameter("nombreMascota");
        String especie = request.getParameter("especie");
        String raza = request.getParameter("raza");
        String edadStr = request.getParameter("edad");
        Integer edad = (edadStr != null && !edadStr.isEmpty()) ? Integer.parseInt(edadStr) : null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {

            // Buscar id_usuario
            String sqlGetIdUsuario = "SELECT id_usuario FROM usuarios WHERE nombre = ?";
            Integer idUsuario = null;
            try (PreparedStatement ps = conn.prepareStatement(sqlGetIdUsuario)) {
                ps.setString(1, nombreUsuario);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idUsuario = rs.getInt("id_usuario");
                } else {
                    request.setAttribute("error", "El usuario con nombre '" + nombreUsuario + "' no existe.");
                    request.getRequestDispatcher("RegistrarMascota.jsp").forward(request, response);
                    return;
                }
            }

            // Insertar mascota y obtener id generado
            String sqlInsert = "INSERT INTO mascota (id_usuario, nombre, especie, raza, edad) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                psInsert.setInt(1, idUsuario);
                psInsert.setString(2, nombreMascota);
                psInsert.setString(3, especie);
                psInsert.setString(4, raza);
                if (edad != null) {
                    psInsert.setInt(5, edad);
                } else {
                    psInsert.setNull(5, Types.INTEGER);
                }
                psInsert.executeUpdate();

                ResultSet generatedKeys = psInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idMascota = generatedKeys.getInt(1);
                    // Puedes pasar un mensaje de éxito o redirigir a otra página
                    request.setAttribute("mensaje", "Mascota registrada correctamente con ID: " + idMascota);
                    request.getRequestDispatcher("RegistrarMascota.jsp").forward(request, response);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la mascota.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar mascota: " + e.getMessage());
            request.getRequestDispatcher("RegistrarMascota.jsp").forward(request, response);
        }
    }
}


