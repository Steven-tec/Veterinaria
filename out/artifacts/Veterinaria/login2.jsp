<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" href="css/loginfinal.css">
  <style>
    .btn-volver {
      display: inline-block;
      margin-top: 15px;
      padding: 10px 20px;
      background-color: #6c757d;
      color: #ffffff;
      border: none;
      border-radius: 5px;
      text-decoration: none;
      font-weight: bold;
      transition: background-color 0.3s ease;
    }

    .btn-volver:hover {
      background-color: #5a6268;
      color: #fff;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>Login</h2>

  <form method="post" action="login2">
    Email:
    <input type="email" name="email" required>
    Password:
    <input type="password" name="password" required>
    <input type="submit" value="Iniciar Sesión">
  </form>

  <% if (request.getParameter("error") != null) { %>
  <p class="error">Email o contraseña incorrectos</p>
  <% } %>

  <div class="register-link">
    ¿No tienes cuenta? <a href="regitrarcliente.jsp">Crear cuenta</a>
  </div>

  <div class="text-center">
    <a href="index.jsp" class="btn-volver">Volver al Inicio</a>
  </div>
</div>
</body>
</html>
