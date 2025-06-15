<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.steven.manejodesesiones.utils.dto.CitaDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Citas Médicas</title>
    <link rel="stylesheet" href="css/reporteCitas.css">
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

