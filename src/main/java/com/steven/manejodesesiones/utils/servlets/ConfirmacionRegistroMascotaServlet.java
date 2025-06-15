package com.steven.manejodesesiones.utils.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/confirmacionMascota")
public class ConfirmacionRegistroMascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el parámetro de la request
        String idMascota = request.getParameter("idMascota");

        // Validar si viene el parámetro, o manejar error aquí si quieres
        if (idMascota == null || idMascota.isEmpty()) {
            idMascota = "Desconocido";
        }

        // Pasar el idMascota como atributo a la vista JSP
        request.setAttribute("idMascota", idMascota);

        // Forward a JSP para mostrar la confirmación
        request.getRequestDispatcher("confirmacionMascota.jsp").forward(request, response);
    }
}
