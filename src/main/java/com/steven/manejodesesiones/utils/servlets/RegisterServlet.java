package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");
        String rol = request.getParameter("rol");
        String hashedPassword = PasswordHash.hashPassword(plainPassword);
        String codigo = GenerateCode.generateCode();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (nombre, apellido, email, password_hash, rol, verificado, codigo_verificacion) VALUES (?, ?, ?, ?, ?, 0, ?)");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.setString(5, rol);
            ps.setString(6, codigo);
            ps.executeUpdate();

            EmailSender.sendEmail(email, "Código de verificación", "Su código es: " + codigo);
            response.sendRedirect("verify.jsp?email=" + email);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("regitrarcliente.jsp?error=1");
        }
    }
}
