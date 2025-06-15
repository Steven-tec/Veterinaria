<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.steven.manejodesesiones.utils.dto.CitaDTO" %>
<html>
<head>
  <title>Listado de Citas Médicas</title>
  <style>
    body {
      background: linear-gradient(135deg, #4CAF50 0%, #4CAF50 100%);
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 20px;
      min-height: 100vh;
      color: #333;
    }

    .container {
      max-width: 1200px;
      margin: 0 auto;
      background: rgba(255, 255, 255, 0.95);
      border-radius: 15px;
      padding: 30px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
      backdrop-filter: blur(10px);
    }

    h2 {
      color: #2E7D32;
      text-align: center;
      margin-bottom: 30px;
      font-size: 2.2em;
      font-weight: 600;
      text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    table {
      width: 100%;
      border-collapse: collapse;
      background: white;
      border-radius: 10px;
      overflow: hidden;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }

    th {
      background: linear-gradient(135deg, #4CAF50, #66BB6A);
      color: white;
      padding: 15px 12px;
      text-align: left;
      font-weight: 600;
      font-size: 0.95em;
      letter-spacing: 0.5px;
      text-transform: uppercase;
    }

    td {
      padding: 12px;
      border-bottom: 1px solid #E8F5E8;
      transition: background-color 0.3s ease;
    }

    tr:hover {
      background-color: #F1F8E9;
    }

    tr:nth-child(even) {
      background-color: #FAFAFA;
    }

    tr:nth-child(even):hover {
      background-color: #F1F8E9;
    }

    .no-data {
      text-align: center;
      padding: 40px;
      color: #666;
      font-size: 1.1em;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }

    .no-data::before {
      content: "📋";
      display: block;
      font-size: 3em;
      margin-bottom: 15px;
    }

    /* Responsive design */
    @media (max-width: 768px) {
      .container {
        padding: 15px;
        margin: 10px;
      }

      table {
        font-size: 0.9em;
      }

      th, td {
        padding: 8px 6px;
      }

      h2 {
        font-size: 1.8em;
      }
    }

    /* Animaciones sutiles */
    .container {
      animation: slideIn 0.6s ease-out;
    }

    @keyframes slideIn {
      from {
        opacity: 0;
        transform: translateY(30px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    table {
      animation: fadeIn 0.8s ease-out 0.2s both;
    }

    @keyframes fadeIn {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }
  </style>
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


