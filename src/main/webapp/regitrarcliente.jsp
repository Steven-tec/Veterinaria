<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>

<!DOCTYPE html>
<html>
<head>
  <title>Registrar Usuario</title>
  <link rel="stylesheet" href="css/registarCliente.css">

</head>
<body>
<div class="form-container">
  <h2>Registrar Usuario</h2>
  <form method="post" action="register">
    Nombre: <input type="text" name="nombre" required><br>
    Apellido: <input type="text" name="apellido" required><br>
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required><br>
    Rol: <input type="text" name="rol" required><br>
    <input type="submit" value="Registrar">
  </form>
</div>
</body>
</html>
