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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VeterinarioDAO dao = new VeterinarioDAO();
        List<VeterinarioDTO> listaVeterinarios = dao.listarVeterinarios();
        request.setAttribute("listaVeterinarios", listaVeterinarios);
        request.getRequestDispatcher("/registrarCita.jsp").forward(request, response);
    }
}
