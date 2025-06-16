<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Usuario</title>

  <link rel="stylesheet" href="css/editarUsuario.css">

</head>
<body>

<div class="container">
  <h2>Editar Usuario</h2>

  <form method="post" action="editarUsuario">
    <input type="hidden" name="id" value="<%= request.getAttribute("id") != null ? request.getAttribute("id") : "" %>">

    <label>Nombre:</label>
    <input type="text" name="nombre" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>

    <label>Apellido:</label>
    <input type="text" name="apellido" value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>" required>

    <label>Email:</label>
    <input type="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>

    <label>Rol:</label>
    <input type="text" name="rol" value="<%= request.getAttribute("rol") != null ? request.getAttribute("rol") : "" %>" required>

    <input type="submit" value="Guardar cambios">
  </form>

  <div class="cancel-link">
    <a href="crudUsuarios">Cancelar</a>
  </div>
</div>

</body>
</html>


