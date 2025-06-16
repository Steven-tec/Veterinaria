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

        VeterinarioDAO dao = new VeterinarioDAO();
        List<VeterinarioDTO> veterinarios = dao.listarVeterinarios();

        request.setAttribute("veterinarios", veterinarios);
        request.getRequestDispatcher("registrarCita.jsp").forward(request, response);
    }
}
