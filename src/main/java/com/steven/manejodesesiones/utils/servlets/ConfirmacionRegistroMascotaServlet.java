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

        // Obtener el parámetro "idMascota" enviado en la solicitud HTTP GET
        String idMascota = request.getParameter("idMascota");

        // Validar si el parámetro "idMascota" está vacío o es nulo
        if (idMascota == null || idMascota.isEmpty()) {
            // Asignar valor por defecto "Desconocido" en caso de que no se reciba el parámetro
            idMascota = "Desconocido";
        }

        // Guardar el valor de "idMascota" como atributo de la solicitud para ser accedido en el JSP
        request.setAttribute("idMascota", idMascota);

        // Redirigir (forward) la solicitud y respuesta al JSP "confirmacionMascota.jsp" para mostrar la confirmación
        request.getRequestDispatcher("confirmacionMascota.jsp").forward(request, response);
    }
}

