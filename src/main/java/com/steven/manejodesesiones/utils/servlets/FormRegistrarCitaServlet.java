package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dao.VeterinarioDAO;
import com.steven.manejodesesiones.utils.dto.VeterinarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/formRegistrarCita")
public class FormRegistrarCitaServlet extends HttpServlet {

    // Método GET para cargar la lista de veterinarios y enviarla a la página de registro de cita
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear instancia del DAO de veterinarios
        VeterinarioDAO dao = new VeterinarioDAO();
        // Obtener la lista de veterinarios desde la base de datos
        List<VeterinarioDTO> listaVeterinarios = dao.listarVeterinarios();
        // Guardar la lista de veterinarios como atributo de la request
        request.setAttribute("listaVeterinarios", listaVeterinarios);
        // Enviar la request a la página JSP para mostrar el formulario de registro de cita
        request.getRequestDispatcher("/registrarCita.jsp").forward(request, response);
    }
}
