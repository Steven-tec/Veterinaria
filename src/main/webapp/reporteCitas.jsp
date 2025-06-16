<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.steven.manejodesesiones.utils.dto.CitaDTO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte de Citas</title>

    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #e0f7fa; /* celeste muy suave */
        }

        /* Navbar con degradado verde - celeste */
        .navbar {
            background: linear-gradient(90deg, #009688, #4db6ac);
        }
        .navbar-brand, .navbar-nav .nav-link, .btn-outline-light {
            color: #e0f2f1 !important;
            font-weight: 600;
            text-shadow: 0 0 3px rgba(0,0,0,0.15);
        }
        .navbar-nav .nav-link.active {
            font-weight: 700;
            color: #004d40 !important; /* verde oscuro */
            background-color: #b2dfdb;
            border-radius: 5px;
        }

        /* Tabla con borde y fondo en tonos suaves */
        table {
            width: 100%;
            margin-top: 20px;
            border: 2px solid #009688;
            background-color: #b2dfdb; /* celeste suave */
            border-radius: 8px;
            overflow: hidden;
        }
        thead {
            background: #004d40; /* verde oscuro */
            color: white;
            font-weight: 700;
        }
        tbody tr:hover {
            background-color: #80cbc4; /* celeste medio al pasar mouse */
        }
        th, td {
            text-align: center;
            vertical-align: middle;
            border: 1px solid #004d40;
        }

        h2 {
            color: #004d40;
            margin-top: 20px;
            font-weight: 700;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Opciones de Administrador</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarAdmin" aria-controls="navbarAdmin" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarAdmin">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/crudUsuarios">Gestión de Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%= request.getContextPath() %>/citas/reporte">Reporte de Citas</a>
                </li>
            </ul>

            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<%= request.getContextPath() %>/LoginAdmin.jsp">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2>Reporte de Citas Médicas</h2>

    <table class="table table-bordered table-striped table-hover">
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
            <td><%= cita.getFecha() != null ? cita.getFecha().toString() : "" %></td>
            <td><%= cita.getHora() %></td>
            <td><%= cita.getMotivo() %></td>
            <td><%= cita.getIndicaciones() %></td>
            <td><%= cita.getVeterinario() %></td>
            <td><%= cita.getEstado() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="8" class="text-center">No hay citas para mostrar.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS y Popper -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>
