<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="container">
    <div class="card">
        <div class="left">
            <img src="img/mascota-login.jpg" alt="Mascota veterinaria">
        </div>
        <div class="right">
            <h2>Inicio de Sesión</h2>

            <!-- Mostrar mensaje de error si existe -->
            <c:if test="${not empty error}">
                <div class="error" style="color:red; margin-bottom: 10px;">
                        ${error}
                </div>
            </c:if>

            <form action="LoginServlet" method="post">
                <input type="email" name="usuario" placeholder="Correo Electrónico" required>
                <input type="password" name="clave" placeholder="Contraseña" required>
                <div class="link">
                    <a href="registro.jsp">¿Necesitas una cuenta?</a>
                </div>
                <input type="submit" value="Iniciar Sesión">
            </form>
        </div>
    </div>
</div>
</body>
</html>
