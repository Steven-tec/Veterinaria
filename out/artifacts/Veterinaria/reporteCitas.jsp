<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.steven.manejodesesiones.utils.dto.CitaDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Citas Médicas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #4CAF 0%, #4CAF50 100%);
            margin: 0;
            padding: 40px;
        }

        h2 {
            text-align: center;
            color: #fff;
            margin-bottom: 30px;
        }

        table {
            background-color: #fff;
            border-collapse: collapse;
            width: 100%;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

<h2>Reporte de Citas Médicas</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>ID Mascota</th>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Motivo</th>
        <th>Indicaciones</th>
        <th>Veterinario</th>
        <th>Estado</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<CitaDTO> citas = (List<CitaDTO>) request.getAttribute("citas");
        if (citas != null && !citas.isEmpty()) {
            for (CitaDTO cita : citas) {
    %>
    <tr>
        <td><%= cita.getId() %></td>
        <td><%= cita.getIdMascota() %></td>
        <td><%= cita.getFecha() %></td>
        <td><%= cita.getHora() %></td>
        <td><%= cita.getMotivo() != null ? cita.getMotivo() : "" %></td>
        <td><%= cita.getIndicaciones() != null ? cita.getIndicaciones() : "" %></td>
        <td><%= cita.getVeterinario() != null ? cita.getVeterinario() : "" %></td>
        <td><%= cita.getEstado() != null ? cita.getEstado() : "" %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="8">No se encontraron citas.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>

