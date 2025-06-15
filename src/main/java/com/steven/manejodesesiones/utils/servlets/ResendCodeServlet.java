package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import com.steven.manejodesesiones.utils.EmailSender;
import com.steven.manejodesesiones.utils.GenerateCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/resend-code")
public class ResendCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email es requerido");
            return;
        }

        // Generamos un nuevo código
        String verificationCode = GenerateCode.generateCode();

        // Guardamos el nuevo código en la base de datos
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE usuarios SET verification_code = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, verificationCode);
                stmt.setString(2, email);

                int updated = stmt.executeUpdate();

                if (updated == 0) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
                    return;
                }
            }

            // Enviamos el nuevo código al correo
            String subject = "Nuevo código de verificación";
            String content = "Su nuevo código de verificación es: " + verificationCode;

            EmailSender.sendEmail(email, subject, content);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Código reenviado correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de base de datos");
        }
    }
}

