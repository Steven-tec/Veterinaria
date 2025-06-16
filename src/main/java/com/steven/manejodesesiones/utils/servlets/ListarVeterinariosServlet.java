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

    // Método que maneja las solicitudes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear instancia del DAO para acceder a los veterinarios
        VeterinarioDAO dao = new VeterinarioDAO();
        // Obtener la lista de veterinarios desde la base de datos
        List<VeterinarioDTO> veterinarios = dao.listarVeterinarios();

        // Imprimir en consola la cantidad de veterinarios encontrados para depuración
        System.out.println("Veterinarios encontrados: " + veterinarios.size());

        // Guardar la lista de veterinarios como atributo en la request
        request.setAttribute("veterinarios", veterinarios);
        // Enviar la request a la página JSP que mostrará la lista de veterinarios
        request.getRequestDispatcher("listarVeterinarios.jsp").forward(request, response);
    }
}
