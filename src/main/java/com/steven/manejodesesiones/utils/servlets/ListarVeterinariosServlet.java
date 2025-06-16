package com.steven.manejodesesiones.utils.servlets;

import com.steven.manejodesesiones.utils.dto.VeterinarioDTO;
import com.steven.manejodesesiones.utils.dao.VeterinarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listarVeterinarios")
public class ListarVeterinariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VeterinarioDAO dao = new VeterinarioDAO();
        List<VeterinarioDTO> veterinarios = dao.listarVeterinarios();

        System.out.println("Veterinarios encontrados: " + veterinarios.size()); // Depuración

        request.setAttribute("veterinarios", veterinarios);
        request.getRequestDispatcher("listarVeterinarios.jsp").forward(request, response);
    }
}
