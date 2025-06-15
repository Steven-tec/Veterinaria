package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String codigo = request.getParameter("codigo");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET verificado = 1 WHERE email = ? AND codigo_verificacion = ?");
            ps.setString(1, email);
            ps.setString(2, codigo);
            int updated = ps.executeUpdate();

            if (updated > 0) {
                response.sendRedirect("verificacionExitosa.jsp");
            } else {
                response.sendRedirect("verify.jsp?email=" + email + "&error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("verify.jsp?email=" + email + "&error=2");
        }
    }
}
