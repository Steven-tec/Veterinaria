<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.steven.manejodesesiones.utils.Usuario" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administración de Usuarios</title>

  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    /* Colores tonos verdes y celestes para el CRUD */

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

    /* Botones con tonos verdes y celestes */
    .btn-primary {
      background-color: #00796b;
      border-color: #004d40;
      font-weight: 600;
      box-shadow: 0 0 6px #004d40;
    }
    .btn-primary:hover {
      background-color: #004d40;
      border-color: #00251a;
    }

    .btn-warning {
      background-color: #4db6ac;
      border-color: #00796b;
      color: #004d40;
      font-weight: 600;
      box-shadow: 0 0 6px #00796b;
    }
    .btn-warning:hover {
      background-color: #00796b;
      border-color: #004d40;
      color: #e0f7fa;
    }

    .btn-danger {
      background-color: #00695c;
      border-color: #004d40;
      font-weight: 600;
      box-shadow: 0 0 6px #004d40;
    }
    .btn-danger:hover {
      background-color: #004d40;
      border-color: #00251a;
    }

    /* Formularios en línea: margen y separación */
    form {
      display: inline-block;
      margin: 0 3px;
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
          <a class="nav-link" href="crudUsuarios">Gestión de Usuarios</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="citas/reporte">Reporte de Citas</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="RegistrarAdministrador.jsp">Registro de Administradores</a>
        </li>

      </ul>

      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="btn btn-outline-light" href="LoginAdmin.jsp">Cerrar Sesión</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
  <h2 class="mb-4" style="color: #004d40;">CRUD de Usuarios</h2>
  <table class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>Email</th>
      <th>Rol</th>
      <th>Estado</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
      if (usuarios != null) {
        for (Usuario u : usuarios) {
    %>
    <tr>
      <td><%= u.getIdUsuario() %></td>
      <td><%= u.getNombre() %></td>
      <td><%= u.getApellido() %></td>
      <td><%= u.getEmail() %></td>
      <td><%= u.getRol() %></td>
      <td><%= u.isActivo() ? "Activo" : "Inactivo" %></td>
      <td>
        <form method="get" action="editarUsuario">
          <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
          <input type="submit" class="btn btn-primary btn-sm" value="Editar">
        </form>

        <form method="post" action="activarDesactivarUsuario">
          <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
          <input type="hidden" name="estado" value="<%= u.isActivo() ? "false" : "true" %>">
          <input type="submit" class="btn btn-warning btn-sm" value="<%= u.isActivo() ? "Desactivar" : "Activar" %>">
        </form>

        <form method="post" action="eliminarUsuario" onsubmit="return confirm('¿Seguro que deseas eliminar este usuario?');">
          <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
          <input type="submit" class="btn btn-danger btn-sm" value="Eliminar">
        </form>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr><td colspan="7" class="text-center">No hay usuarios para mostrar.</td></tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>

<!-- Bootstrap JS y Popper (para funcionalidad responsive del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>


