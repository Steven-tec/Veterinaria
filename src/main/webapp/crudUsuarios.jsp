<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.steven.manejodesesiones.utils.Usuario" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administración de Usuarios</title>

  <link rel="stylesheet" href="css/adminUsuarios.css">

</head>
<body>

<h2>CRUD de Usuarios</h2>

<table>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Email</th>
    <th>Rol</th>
    <th>Estado</th>
    <th>Acciones</th>
  </tr>

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
      <!-- Botón Editar -->
      <form method="get" action="editarUsuario">
        <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
        <input type="submit" value="Editar">
      </form>

      <!-- Botón Activar/Desactivar -->
      <form method="post" action="activarDesactivarUsuario">
        <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
        <input type="hidden" name="estado" value="<%= u.isActivo() ? "false" : "true" %>">
        <input type="submit" value="<%= u.isActivo() ? "Desactivar" : "Activar" %>">
      </form>

      <!-- Botón Eliminar -->
      <form method="post" action="eliminarUsuario" onsubmit="return confirm('¿Seguro que deseas eliminar este usuario?');">
        <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
        <input type="submit" value="Eliminar">
      </form>
    </td>
  </tr>
  <%
      }
    }
  %>
</table>

</body>
</html>


