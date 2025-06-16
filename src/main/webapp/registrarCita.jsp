<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.steven.manejodesesiones.utils.dto.VeterinarioDTO" %>
<%
  // Veterinarios hardcodeados para prueba
  List<VeterinarioDTO> veterinarios = new ArrayList<>();
  veterinarios.add(new VeterinarioDTO(1L, "Dr. Juan Pérez"));
  veterinarios.add(new VeterinarioDTO(2L, "Dra. Ana Gómez"));
  veterinarios.add(new VeterinarioDTO(3L, "Dr. Carlos Martínez"));

  request.setAttribute("veterinarios", veterinarios);

  String veterinarioSeleccionado = request.getParameter("veterinario") != null ? request.getParameter("veterinario") : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <title>Registrar Cita Médica</title>
  <!-- Bootstrap 5 CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    body {
      background: #e6f2f0; /* verde azulado muy suave */
      color: #0d3b2e; /* verde oscuro */
    }
    .container {
      max-width: 600px;
      margin-top: 40px;
      background: #c7e4d4; /* verde claro */
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 0 15px rgba(13, 59, 46, 0.3);
    }
    h2 {
      text-align: center;
      color: #065f46; /* verde medio */
      margin-bottom: 25px;
      font-weight: 700;
    }
    label {
      font-weight: 600;
      color: #064e3b; /* verde oscuro */
    }
    input[type="submit"] {
      background-color: #0f766e; /* verde azulado */
      border: none;
    }
    input[type="submit"]:hover {
      background-color: #115e59;
    }
    /* Estilos para botón volver */
    .btn-volver {
      color: #6c757d;
      border: 1px solid #ced4da;
      font-weight: 600;
      background-color: #fff;
      transition: background-color 0.3s ease, color 0.3s ease;
    }
    .btn-volver:hover {
      background-color: #adb5bd;
      color: #fff;
      text-decoration: none;
    }
  </style>
</head>
<body>
<div class="container shadow">
  <h2>Registrar Cita Médica</h2>

  <%-- Mensajes dinámicos de éxito o error --%>
  <% String exito = (String) request.getAttribute("exito");
    String error = (String) request.getAttribute("error");
    if (exito != null) { %>
  <div class="alert alert-success" role="alert">
    <%= exito %>
  </div>
  <% } else if (error != null) { %>
  <div class="alert alert-danger" role="alert">
    <%= error %>
  </div>
  <% } %>

  <form method="post" action="registrarCita" class="needs-validation" novalidate>
    <div class="mb-3">
      <label for="idMascota" class="form-label">ID Mascota</label>
      <input type="number" class="form-control" id="idMascota" name="idMascota" required
             value="<%= request.getParameter("idMascota") != null ? request.getParameter("idMascota") : "" %>">
      <div class="invalid-feedback">
        Por favor ingresa el ID de la mascota.
      </div>
    </div>

    <div class="mb-3">
      <label for="fecha" class="form-label">Fecha</label>
      <input type="date" class="form-control" id="fecha" name="fecha" required
             value="<%= request.getParameter("fecha") != null ? request.getParameter("fecha") : "" %>">
      <div class="invalid-feedback">
        Por favor selecciona una fecha.
      </div>
    </div>

    <div class="mb-3">
      <label for="hora" class="form-label">Hora</label>
      <input type="time" class="form-control" id="hora" name="hora" required
             value="<%= request.getParameter("hora") != null ? request.getParameter("hora") : "" %>">
      <div class="invalid-feedback">
        Por favor ingresa la hora.
      </div>
    </div>

    <div class="mb-3">
      <label for="motivo" class="form-label">Motivo</label>
      <input type="text" class="form-control" id="motivo" name="motivo"
             value="<%= request.getParameter("motivo") != null ? request.getParameter("motivo") : "" %>">
    </div>

    <div class="mb-3">
      <label for="indicaciones" class="form-label">Indicaciones</label>
      <textarea class="form-control" id="indicaciones" name="indicaciones" rows="3"><%= request.getParameter("indicaciones") != null ? request.getParameter("indicaciones") : "" %></textarea>
    </div>

    <div class="mb-3">
      <label for="veterinario" class="form-label">Veterinario</label>
      <select class="form-select" id="veterinario" name="veterinario" required>
        <option value="">-- Selecciona un veterinario --</option>
        <%
          for (VeterinarioDTO v : veterinarios) {
            String selected = v.getId().toString().equals(veterinarioSeleccionado) ? "selected" : "";
        %>
        <option value="<%= v.getId() %>" <%= selected %>><%= v.getNombre() %></option>
        <%
          }
        %>
      </select>
      <div class="invalid-feedback">
        Por favor selecciona un veterinario.
      </div>
    </div>

    <button type="submit" class="btn btn-primary w-100 mb-3">Registrar</button>
  </form>

  <a href="<%= request.getContextPath() %>/" class="btn btn-volver w-100">
    Volver al inicio
  </a>
</div>

<script>
  // Bootstrap form validation
  (function () {
    'use strict'
    var forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
            .forEach(function (form) {
              form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                  event.preventDefault()
                  event.stopPropagation()
                }
                form.classList.add('was-validated')
              }, false)
            })
  })()
</script>
</body>
</html>

