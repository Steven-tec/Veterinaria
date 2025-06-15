<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.steven.manejodesesiones.utils.dto.CitaDTO" %>
<html>
<head>
  <title>Listado de Citas Médicas</title>
  <link rel="stylesheet" href="css/verCitas.css">
</head>
<body>
<div class="container">
  <h2>Listado de Citas Médicas</h2>
  <%
    List<CitaDTO> citas = (List<CitaDTO>) request.getAttribute("citas");
    if (citas != null && !citas.isEmpty()) {
  %>
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
    <% for (CitaDTO cita : citas) { %>
    <tr>
      <td><%= cita.getId() %></td>
      <td><%= cita.getIdMascota() %></td>
      <td><%= cita.getFecha() %></td>
      <td><%= cita.getHora() %></td>
      <td><%= cita.getMotivo() %></td>
      <td><%= cita.getIndicaciones() %></td>
      <td><%= cita.getVeterinario() %></td>
      <td><%= cita.getEstado() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <%
  } else {
  %>
  <div class="no-data">
    <p>No hay citas registradas.</p>
  </div>
  <%
    }
  %>
</div>
</body>
</html>


