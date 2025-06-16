package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import com.steven.manejodesesiones.utils.EmailSender;
import com.steven.manejodesesiones.utils.PasswordHash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/RegistrarAdministradorServlet")
public class RegistrarAdministradorServlet extends HttpServlet {

    private String generarCodigoVerificacion() {
        int codigo = (int) (Math.random() * 900000) + 100000; // genera 6 dígitos entre 100000 y 999999
        return String.valueOf(codigo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String rol = "ADMINISTRADOR";

        // Generar código de verificación de 6 dígitos
        String codigoVerificacion = generarCodigoVerificacion();

        // Hash de la contraseña
        String hashedPassword = PasswordHash.hashPassword(clave);

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO usuarios (nombre, apellido, email, password_hash, rol, verificado, codigo_verificacion, fecha_registro, activo) "
                    + "VALUES (?, ?, ?, ?, ?, 0, ?, NOW(), 1)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            stmt.setString(5, rol);
            stmt.setString(6, codigoVerificacion);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                // Enviar correo con código de verificación
                String subject = "Verificación de cuenta - Administrador";
                String content = "Hola " + nombre + ",\n\n"
                        + "Gracias por registrarte como administrador. Para activar tu cuenta, por favor utiliza el siguiente código de verificación de 6 dígitos:\n\n"
                        + codigoVerificacion + "\n\n"
                        + "O ingresa a esta página para verificar tu cuenta:\n"
                        + "http://tu-dominio.com/verify.jsp?email=" + email + "\n\n"
                        + "Saludos.";

                EmailSender.sendEmail(email, subject, content);

                // Redirigir a página para ingresar código de verificación
                response.sendRedirect("verify.jsp?email=" + email);
            } else {
                // No se pudo insertar el usuario
                response.sendRedirect("RegistrarAdministrador.jsp?msg=Error al registrar administrador");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("RegistrarAdministrador.jsp?msg=Error interno del servidor");
        }
    }
}

