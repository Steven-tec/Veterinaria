<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login Administrador</title>
  <link rel="stylesheet" href="css/loginfinal.css">
</head>
<body>

<div class="login-box">
  <h2>Login Administrador</h2>
  <form action="LoginAdminServlet" method="post">
    <input type="email" name="email" placeholder="Correo" required>
    <input type="password" name="password" placeholder="Contraseña" required>
    <button type="submit">Ingresar</button>
  </form>
</div>

</body>
</html>


