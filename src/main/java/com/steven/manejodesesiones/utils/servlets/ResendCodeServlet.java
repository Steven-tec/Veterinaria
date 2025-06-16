package com.steven.manejodesesiones.utils.servlets;

// Importa la clase para gestionar conexiones a la base de datos
import com.steven.manejodesesiones.utils.DBConnection;
// Importa la clase para enviar correos electrónicos
import com.steven.manejodesesiones.utils.EmailSender;
// Importa la clase para generar códigos de verificación aleatorios
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

// Define un servlet que responde a la ruta /resend-code
@WebServlet("/resend-code")
public class ResendCodeServlet extends HttpServlet {

    // Método que maneja solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el parámetro "email" enviado en la solicitud
        String email = request.getParameter("email");

        // Valida que el email no sea nulo ni vacío
        if (email == null || email.isEmpty()) {
            // Si es inválido, responde con error 400 Bad Request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email es requerido");
            return;
        }

        // Genera un nuevo código de verificación utilizando la clase GenerateCode
        String verificationCode = GenerateCode.generateCode();

        // Intenta establecer conexión con la base de datos y actualizar el código
        try (Connection conn = DBConnection.getConnection()) {

            // Consulta SQL para actualizar el código de verificación del usuario con el email dado
            String sql = "UPDATE usuarios SET verification_code = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Asigna el nuevo código y el email como parámetros del PreparedStatement
                stmt.setString(1, verificationCode);
                stmt.setString(2, email);

                // Ejecuta la actualización y obtiene la cantidad de filas afectadas
                int updated = stmt.executeUpdate();

                // Si no se actualizó ninguna fila, significa que el usuario no existe
                if (updated == 0) {
                    // Responde con error 404 Not Found
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
                    return;
                }
            }

            // Prepara el asunto y contenido del correo con el nuevo código
            String subject = "Nuevo código de verificación";
            String content = "Su nuevo código de verificación es: " + verificationCode;

            // Envía el correo electrónico con el nuevo código al usuario
            EmailSender.sendEmail(email, subject, content);

            // Responde con código 200 OK y un mensaje indicando éxito
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Código reenviado correctamente");

        } catch (SQLException e) {
            // En caso de error en la base de datos, imprime la traza del error
            e.printStackTrace();
            // Responde con error 500 Internal Server Error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de base de datos");
        }
    }
}


