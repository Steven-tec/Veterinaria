package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dao.VeterinarioDAO;
import com.steven.manejodesesiones.utils.dto.VeterinarioDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/mostrarFormularioCita")
public class MostrarFormularioCitaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Crear instancia del DAO para acceder a los datos de veterinarios
        VeterinarioDAO dao = new VeterinarioDAO();

        // Obtener la lista de veterinarios desde la base de datos
        List<VeterinarioDTO> veterinarios = dao.listarVeterinarios();

        // Guardar la lista de veterinarios como atributo en la request para que esté disponible en JSP
        request.setAttribute("veterinarios", veterinarios);

        // Redirigir la solicitud a la página registrarCita.jsp para mostrar el formulario con veterinarios
        request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
    }
}

