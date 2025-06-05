package com.steven.manejodesesiones.veterinaria;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegistroMascotaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String edad = request.getParameter("edad");

        // Aquí puedes guardar en base de datos en el futuro

        RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
        rd.forward(request, response);
    }
}
