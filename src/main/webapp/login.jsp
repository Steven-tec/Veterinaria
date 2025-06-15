<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="css/loginfinal.css">

</head>
<body>
<div class="login-container">
    <h2>Login</h2>

    <form method="post" action="login">
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
</div>
</body>
</html>

