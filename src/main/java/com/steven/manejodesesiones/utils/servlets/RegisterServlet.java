package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");
        // Forzar rol CLIENTE para nuevos registros
        String rol = "CLIENTE";

        // Validar que nombre y apellido solo contengan letras y espacios
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        if (!nombre.matches(regex) || !apellido.matches(regex)) {
            request.setAttribute("error", "nombre_invalido");
            request.getRequestDispatcher("regitrarcliente.jsp").forward(request, response);
            return;
        }

        // Validar que la contraseña tenga al menos 8 caracteres
        if (plainPassword == null || plainPassword.length() < 8) {
            request.setAttribute("error", "password_corta");
            request.getRequestDispatcher("regitrarcliente.jsp").forward(request, response);
            return;
        }

        // Hashear la contraseña para guardar de forma segura
        String hashedPassword = PasswordHash.hashPassword(plainPassword);
        // Generar código de verificación para el usuario
        String codigo = GenerateCode.generateCode();

        try (Connection conn = DBConnection.getConnection()) {

            // Verificar si ya existe un usuario con el mismo email
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM usuarios WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                request.setAttribute("error", "email_duplicado");
                request.getRequestDispatcher("regitrarcliente.jsp").forward(request, response);
                return;
            }
            rs.close();
            checkStmt.close();

            // Insertar nuevo usuario con datos proporcionados y código de verificación
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO usuarios (nombre, apellido, email, password_hash, rol, verificado, codigo_verificacion) VALUES (?, ?, ?, ?, ?, 0, ?)"
            );
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.setString(5, rol);
            ps.setString(6, codigo);
            ps.executeUpdate();

            // Preparar mensaje de correo para enviar código de verificación al usuario
            String ClinicaMascotas = "CLINICA MASCOTAS FELICES";
            String mensajeCorreo = "Hola " + nombre + ",\n\n" +
                    "¡Gracias por registrarte en " + ClinicaMascotas + "!\n\n" +
                    "Para completar tu registro y activar tu cuenta, por favor ingresa el siguiente código de verificación en la página correspondiente:\n\n" +
                    "Código: " + codigo + "\n\n" +
                    "Si no solicitaste este registro, por favor ignora este correo.\n\n" +
                    "¡Bienvenido(a) a nuestra comunidad!\n\n" +
                    "Saludos cordiales,\n" +
                    "El equipo de " + ClinicaMascotas;

            // Enviar correo con el código de verificación
            EmailSender.sendEmail(email, "Código de verificación - Bienvenido a " + ClinicaMascotas, mensajeCorreo);

            // Redirigir a página de verificación con el email como parámetro
            response.sendRedirect("verify.jsp?email=" + email);

        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error, mostrar mensaje genérico y regresar al formulario
            request.setAttribute("error", "error_generico");
            request.getRequestDispatcher("regitrarcliente.jsp").forward(request, response);
        }
    }
}


